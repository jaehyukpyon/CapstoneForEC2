package com.example.demo.gonggongdata.abandoned_animal.controller;

import com.example.demo.gonggongdata.abandoned_animal.response.SidoResponse_j;
import com.example.demo.gonggongdata.abandoned_animal.response.SigunguResponse_j;
import com.example.demo.gonggongdata.abandoned_animal.response.SimpleResponse_j;
import com.example.demo.gonggongdata.abandoned_animal.service.SidoSigunguService_j;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noauth")
@AllArgsConstructor
@Log4j2
public class SidoSigunguController_j {

    private final SidoSigunguService_j sidoSigunguService;

    @GetMapping("/sido-list")
    public ResponseEntity<SidoResponse_j> getAllSido() {
        SidoResponse_j response = sidoSigunguService.getSidoList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sigungu-list")
    public ResponseEntity<?> getSigunguList(@RequestParam(required = false, defaultValue = "") String sidoName) {
        if (sidoName.equals("") || sidoName == null) {
            log.info("sidoName: " + sidoName);
            return ResponseEntity.badRequest().body(new SimpleResponse_j("시도이름을 반드시 포함하여 요청해야 합니다."));
        }
        // 상위 시도 코드 갖고오기
        String sidoCode = sidoSigunguService.getSidoCode(sidoName);

        // 갖고온 시도 코드로 해당 시도 코드를 가진 시군구 리스트들 갖고오기
        SigunguResponse_j response = sidoSigunguService.getSigunguList(sidoCode, sidoName);
        return ResponseEntity.ok(response);
    }
}
