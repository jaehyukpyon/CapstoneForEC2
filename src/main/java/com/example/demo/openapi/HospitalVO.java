package com.example.demo.openapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HospitalVO {
    private Integer id;
    private String hid;
    private Integer uid;
    private Integer rating;
    private String comment;
}
