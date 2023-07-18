package com.example.demo.gonggongdata.abandoned_animal.mapper;

import com.example.demo.gonggongdata.abandoned_animal.dto.TestDto1_j;
import com.example.demo.gonggongdata.abandoned_animal.dto.AnimalListDto_j;
import com.example.demo.gonggongdata.abandoned_animal.response.Item_j;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AbandonedAnimalMapper_j {

    Integer insertAnimalList(List<Item_j> lists);

    Integer deleteAllAnimalFromTable();

    List<Item_j> getExpiredAnimalList(String expiredDate);

    Integer insertExpiredAnimalList(List<Item_j> lists);

    void deleteExpiredAnimalFromAbandonedAnimalListTbl(String expiredDate);

    void test(List<TestDto1_j> list);

    List<AnimalListDto_j> getAllAnimalWithPagination(int startRowNum);

    int getTotalCount();

    int getCountFilteredBySidoSigungu(Map<String, Object> map);

    List<AnimalListDto_j> getAnimalFilteredBySidoSigungu(Map<String, Object> map);
}
