package com.example.demo.gonggongdata.abandoned_animal.response;

import lombok.Data;

import java.util.List;

@Data
public class SigunguResponse_j {

    private Integer totalCount;
    private String sidoName;
    private List<String> sigunguList;
}
