package com.example.demo.gonggongdata.abandoned_animal.controller;

import com.example.demo.gonggongdata.abandoned_animal.dto.AnimalListDto_j;
import com.example.demo.gonggongdata.abandoned_animal.response.AnimalSearchListResponse_j;
import com.example.demo.gonggongdata.abandoned_animal.service.AbandonedAnimalService_j;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/noauth/abandoned-animal-list")
@Log4j2
public class AbandonedAnimalController_j {

    private final AbandonedAnimalService_j abandonedAnimalService;

    @GetMapping("")
    public ResponseEntity<?> getAllAnimalList(@RequestParam(required = false, defaultValue = "1") String pageNo) {
        int page = Integer.valueOf(pageNo);
        int startRowNum = (page - 1) * 10;
        List<AnimalListDto_j> allAnimalWithPagination = abandonedAnimalService.getAllAnimalWithPagination(startRowNum);
        AnimalSearchListResponse_j animalSearchListResponse = new AnimalSearchListResponse_j();
        animalSearchListResponse.setList(allAnimalWithPagination);
        animalSearchListResponse.setPageNo(page);
        animalSearchListResponse.setRealCount(allAnimalWithPagination.size());

        int totalCount = abandonedAnimalService.getTotalCount();
        animalSearchListResponse.setTotalCount(totalCount);
        int totalPagesRequired = (int) Math.ceil(totalCount / 10.0);
        animalSearchListResponse.setTotalPagesRequired(totalPagesRequired);
        animalSearchListResponse.setHasNextPage(page < totalPagesRequired ? "y" : "n");
        return ResponseEntity.ok(animalSearchListResponse);
    }

    @GetMapping("/sido-sigungu")
    public ResponseEntity<?> getAnimalListFilteredBySidoSigungu(
            @RequestParam(required = false, defaultValue = "") String sido,
            @RequestParam(required = false, defaultValue = "") String sigungu,
            @RequestParam(required = false, defaultValue = "1") String pageNo) {

        int page = Integer.valueOf(pageNo);
        int startRowNum = (page - 1) * 10;

        if (sido.equals("") || sigungu.equals("")) {
            Map<String, String> errors = new HashMap<>();
            errors.put("message", "sido 문자열 및 sigungu 문자열은 필수 값입니다.");
            errors.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            return ResponseEntity.badRequest().body(errors); // 400
        }

        // sido, sigungu로 필터링 된 유기동물 총 개수 확인하기
        Map<String, Object> map = new HashMap<>();
        map.put("startRowNum", startRowNum);

        if (sido.contains("제주") || sigungu.contains("제주")) {
            map.put("sido", "제주");
            map.put("sigungu", "제주");
        } else {
            map.put("sido", sido);
            map.put("sigungu", sigungu);
        }

        int countFilteredBySidoSigungu = abandonedAnimalService.getCountFilteredBySidoSigungu(map);
        log.info("countFilteredBySidoSigungu: " + countFilteredBySidoSigungu);

        int totalPagesRequired = (int) Math.ceil(countFilteredBySidoSigungu / 10.0);

        List<AnimalListDto_j> animalFilteredBySidoSigungu = abandonedAnimalService.getAnimalFilteredBySidoSigungu(map);
        AnimalSearchListResponse_j animalSearchListResponse_j = new AnimalSearchListResponse_j();
        animalSearchListResponse_j.setPageNo(page);
        animalSearchListResponse_j.setRealCount(animalFilteredBySidoSigungu.size());
        animalSearchListResponse_j.setTotalCount(countFilteredBySidoSigungu);
        animalSearchListResponse_j.setTotalPagesRequired(totalPagesRequired);
        animalSearchListResponse_j.setHasNextPage(page < totalPagesRequired ? "y" : "n");
        animalSearchListResponse_j.setList(animalFilteredBySidoSigungu);

        return ResponseEntity.ok(animalSearchListResponse_j);
    }
}
