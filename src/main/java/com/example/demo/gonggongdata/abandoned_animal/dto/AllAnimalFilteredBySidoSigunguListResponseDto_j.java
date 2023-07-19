package com.example.demo.gonggongdata.abandoned_animal.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllAnimalFilteredBySidoSigunguListResponseDto_j {

    private Integer totalCount;
    private List<AnimalListDto_j> list;
}
