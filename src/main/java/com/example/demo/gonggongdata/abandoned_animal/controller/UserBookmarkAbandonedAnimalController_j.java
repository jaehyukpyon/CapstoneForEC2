package com.example.demo.gonggongdata.abandoned_animal.controller;

import com.example.demo.gonggongdata.abandoned_animal.dto.AnimalListDto_j;
import com.example.demo.gonggongdata.abandoned_animal.dto.BookmarkDto_j;
import com.example.demo.gonggongdata.abandoned_animal.response.BookmarkListResponseDto_j;
import com.example.demo.gonggongdata.abandoned_animal.service.BookmarkService_j;
import com.example.demo.vo.AuthUserVo_j;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/abandoned-animal")
public class UserBookmarkAbandonedAnimalController_j {

    private final BookmarkService_j bookmarkService;

    @PostMapping("/bookmark")
    public ResponseEntity<?> addAnimalToUserBookmark(@RequestBody BookmarkDto_j bookmarkDto, Authentication authentication) {
        String desertionNo = bookmarkDto.getDesertionNo();
        AuthUserVo_j user = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = user.getId();
        Map<String, String> map = new HashMap<>();
        // 이미 추가 되어있는지 확인
        boolean checkAlreadyBookmarked = bookmarkService.alreadyBookmarked(userId, desertionNo);
        if (checkAlreadyBookmarked) {
            map.put("message", "이미 즐겨찾기에 추가되어 있습니다.");
            return new ResponseEntity<>(map, HttpStatus.CONFLICT); // 409 conflict
        }

        boolean checkAdded = bookmarkService.addToBookmark(userId, desertionNo);
        if (checkAdded) {
            map.put("message", "즐겨찾기에 정상적으로 추가되었습니다.");
            return new ResponseEntity<>(map, HttpStatus.CREATED); // 201 created
        } else {
            map.put("message", "즐겨찾기에 추가 중 오류 발생.");
            return ResponseEntity.internalServerError().body(map); // 500 internal server error
        }
    }

    @DeleteMapping("/bookmark")
    public ResponseEntity<?> deleteAnimalBookmark(@RequestBody BookmarkDto_j bookmarkDto, Authentication authentication) {
        String desertionNo = bookmarkDto.getDesertionNo();
        AuthUserVo_j user = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = user.getId();
        Map<String, String> map = new HashMap<>();

        boolean checkBookmarked = bookmarkService.alreadyBookmarked(userId, desertionNo);
        if (!checkBookmarked) {
            map.put("message", "즐겨찾기에 추가되어 있지 않습니다. 즐겨찾기 리스트에서 삭제할 수 없습니다.");
            return ResponseEntity.badRequest().body(map); // 400 bad request
        }

        boolean checkDeleted = bookmarkService.deleteBookmark(userId, desertionNo);
        if (checkDeleted) {
            map.put("message", "즐겨찾기 리스트에서 정상적으로 삭제되었습니다.");
            return new ResponseEntity<>(map, HttpStatus.OK); // 200
        } else {
            map.put("message", "즐겨찾기 리스트에서 삭제 중 오류 발생.");
            return ResponseEntity.internalServerError().body(map); // 500 internal server error
        }
    }

    @GetMapping("/bookmark/list")
    public ResponseEntity<?> getUserBookmarkList(@RequestParam(required = false, defaultValue = "1") String pageNo, Authentication authentication) {
        int page = Integer.parseInt(pageNo);
        int startRowNum = (page - 1) * 10;

        AuthUserVo_j user = (AuthUserVo_j) authentication.getPrincipal();
        Integer userId = user.getId();

        int totalCount = bookmarkService.getTotalCount(userId);
        int totalPagesRequired = (int) Math.ceil(totalCount / 10.0);

        // 일단 Bookmark table 에서 유저가 북마크한 리스트 전부 다 갖고 오기
        List<String> listDesertionNumbers = bookmarkService.getUserBookmarkListBy10(userId, startRowNum);

        if (listDesertionNumbers.size() > 0) {
            List<AnimalListDto_j> list = bookmarkService.getAnimalList(listDesertionNumbers);
            BookmarkListResponseDto_j response = new BookmarkListResponseDto_j();
            response.setPageNo(page);
            response.setRealCount(list.size());
            response.setTotalCount(totalCount);
            response.setTotalPagesRequired(totalPagesRequired);
            response.setHasNextPage(page < totalPagesRequired ? "y" : "n");
            response.setList(list);
            return ResponseEntity.ok(response);
        } else {
            BookmarkListResponseDto_j response = new BookmarkListResponseDto_j();
            response.setPageNo(1);
            response.setRealCount(0);
            response.setTotalCount(0);
            response.setTotalPagesRequired(0);
            response.setHasNextPage("n");
            response.setList(new ArrayList<>());
            return ResponseEntity.ok(response);
        }
    }
}
