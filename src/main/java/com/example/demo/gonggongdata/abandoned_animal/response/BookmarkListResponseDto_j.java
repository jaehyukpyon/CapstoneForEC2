package com.example.demo.gonggongdata.abandoned_animal.response;

import com.example.demo.gonggongdata.abandoned_animal.dto.AnimalListDto_j;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkListResponseDto_j {

    private Integer pageNo;
    private Integer realCount;
    private Integer totalCount;
    private Integer totalPagesRequired;
    private String hasNextPage;
    private List<AnimalListDto_j> list;
}
