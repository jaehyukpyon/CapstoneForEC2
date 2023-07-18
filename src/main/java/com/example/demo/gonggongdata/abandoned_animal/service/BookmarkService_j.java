package com.example.demo.gonggongdata.abandoned_animal.service;

import com.example.demo.gonggongdata.abandoned_animal.dto.AnimalListDto_j;
import com.example.demo.gonggongdata.abandoned_animal.mapper.BookmarkMapper_j;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookmarkService_j {

    private final BookmarkMapper_j bookmarkMapper;

    public boolean alreadyBookmarked(int userId, String desertionNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("desertionNo", desertionNo);

        int result = bookmarkMapper.checkAlreadyBookmarked(map);
        return result > 0 ? true : false;
    }

    public boolean addToBookmark(int userId, String desertionNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("desertionNo", desertionNo);

        int result = bookmarkMapper.addToBookmark(map);
        return result == 1 ? true : false;
    }

    public boolean deleteBookmark(Integer userId, String desertionNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("desertionNo", desertionNo);

        int result = bookmarkMapper.deleteBookmark(map);
        return result == 1 ? true : false;
    }

    public List<String> getUserBookmarkListBy10(Integer userId, Integer startRowNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("startRowNum", startRowNum);

        List<String> result = bookmarkMapper.getUserBookmarkListBy10(map);
        return result;
    }

    public List<AnimalListDto_j> getAnimalList(List<String> listDesertionNumbers) {
        Set<String> allSet = new HashSet<>();
        for (String str : listDesertionNumbers) {
            allSet.add(str);
        }
        Map<String, AnimalListDto_j> map = new HashMap<>();

        List<AnimalListDto_j> fromNotExpiredTableList = bookmarkMapper.getAnimalList(listDesertionNumbers);
        for (AnimalListDto_j dto : fromNotExpiredTableList) {
            map.put(dto.getDesertionNo(), dto);
            allSet.remove(dto.getDesertionNo());
        }

        List<String> remain = new ArrayList<>();
        if (allSet.size() > 0) {
            for (String str : allSet) {
                remain.add(str);
            }
            List<AnimalListDto_j> fromExpiredTableList = bookmarkMapper.getAnimalListFromExpiredTable(remain);
            for (AnimalListDto_j dto : fromExpiredTableList) {
                map.put(dto.getDesertionNo(), dto);
                allSet.remove(dto.getDesertionNo());
            }
        }
        List<AnimalListDto_j> result = new ArrayList<>();
        for (String str : listDesertionNumbers) {
            result.add(map.get(str));
        }
        return result;
    }

    public int getTotalCount(Integer userId) {
        return bookmarkMapper.getTotalCountBookmark(userId);
    }
}
