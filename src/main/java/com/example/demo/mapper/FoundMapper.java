package com.example.demo.mapper;

import com.example.demo.domain.Found;
import com.example.demo.dto.FoundDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FoundMapper {

    void saveFound(Found found);

    void updateFound(@Param("foundId")int foundId, @Param("foundDto") FoundDto foundDto);

    void deleteFound(@Param("foundId")int foundId);

}
