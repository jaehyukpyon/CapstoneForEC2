package com.example.demo.gonggongdata.abandoned_animal.response;

import lombok.Data;

import java.util.List;

@Data
public class AnimalKindResponse_j {

    private Integer totalCount;
    private List<String> kinds;
}
