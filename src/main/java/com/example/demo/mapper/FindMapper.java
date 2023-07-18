package com.example.demo.mapper;

import com.example.demo.dto.FindDto;
import com.example.demo.domain.Find;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FindMapper {

    void saveFind(Find find);

    void updateFind(@Param("findId")int findId, @Param("findDto") FindDto findDto);

    void deleteFind(@Param("findId")int findId);

}
