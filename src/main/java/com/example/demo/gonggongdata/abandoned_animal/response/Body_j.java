package com.example.demo.gonggongdata.abandoned_animal.response;

import lombok.Data;

@Data
public class Body_j {

    private Items_j items;
    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;
}
