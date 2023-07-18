package com.example.demo.gonggongdata.abandoned_animal.mapper;

import com.example.demo.gonggongdata.abandoned_animal.dto.AnimalListDto_j;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookmarkMapper_j {

    int checkAlreadyBookmarked(Map<String, Object> map);

    int addToBookmark(Map<String, Object> map);

    int deleteBookmark(Map<String, Object> map);

    List<String> getUserBookmarkListBy10(Map<String, Object> map);

    List<AnimalListDto_j> getAnimalList(List<String> listDesertionNumbers);

    List<AnimalListDto_j> getAnimalListFromExpiredTable(List<String> remain);

    int getTotalCountBookmark(Integer userId);
}
