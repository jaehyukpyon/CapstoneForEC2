package com.example.demo.gonggongdata.abandoned_animal.mapper;

import com.example.demo.gonggongdata.abandoned_animal.dto.SidoDto_j;
import com.example.demo.gonggongdata.abandoned_animal.dto.SigunguDto_j;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SidoSigunguMapper_j {

    List<SidoDto_j> getSidoList();
    String getSidoCode(String sidoName);
    List<SigunguDto_j> getSigunguList(String sidoCode);
}
