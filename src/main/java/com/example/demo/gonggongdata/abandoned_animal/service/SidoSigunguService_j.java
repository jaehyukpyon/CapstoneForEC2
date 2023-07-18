package com.example.demo.gonggongdata.abandoned_animal.service;

import com.example.demo.gonggongdata.abandoned_animal.dto.SidoDto_j;
import com.example.demo.gonggongdata.abandoned_animal.dto.SigunguDto_j;
import com.example.demo.gonggongdata.abandoned_animal.mapper.SidoSigunguMapper_j;
import com.example.demo.gonggongdata.abandoned_animal.response.SidoResponse_j;
import com.example.demo.gonggongdata.abandoned_animal.response.SigunguResponse_j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SidoSigunguService_j {

    private final SidoSigunguMapper_j sidoSigunguMapper;

    public SidoResponse_j getSidoList() {
        List<SidoDto_j> sidoList = sidoSigunguMapper.getSidoList();
        List<String> sidoToString = new ArrayList<>();
        sidoList.stream()
                .forEach(sido -> sidoToString.add(sido.getOrgDownNm()));
        SidoResponse_j response = new SidoResponse_j();
        response.setSidoList(sidoToString);
        response.setTotalCount(sidoToString.size());
        return response;
    }

    public String getSidoCode(String sidoName) {
        String sidoCode = sidoSigunguMapper.getSidoCode(sidoName);
        return sidoCode;
    }

    public SigunguResponse_j getSigunguList(String sidoCode, String sidoName) {
        List<SigunguDto_j> sigunguList = sidoSigunguMapper.getSigunguList(sidoCode);
        List<String> siGunguToString = new ArrayList<>();
        sigunguList.stream()
                .forEach(sigungu -> siGunguToString.add(sigungu.getOrgdownnm()));
        SigunguResponse_j response = new SigunguResponse_j();
        response.setSigunguList(siGunguToString);
        response.setTotalCount(siGunguToString.size());
        response.setSidoName(sidoName);
        return response;
    }
}
