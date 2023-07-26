package com.example.demo.mapper;

import com.example.demo.dto.FindDto;
import com.example.demo.domain.Find;
import com.example.demo.vo.FindVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FindMapper {

    void saveFind(Find find);

    void updateFind(@Param("findId")int findId, @Param("findDto") FindDto findDto);

    void deleteFind(@Param("findId")int findId);

    FindDto getFindDto(@Param("findId")int findId);

    public List<FindVO> getList();
}
