package com.example.demo.openapi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoadVO {
    private String loadId;
    private String isOpen;
    private String phoneNum;
    private String address;
    private String postNum;
    private String name;
    private String x;
    private String y;
}
