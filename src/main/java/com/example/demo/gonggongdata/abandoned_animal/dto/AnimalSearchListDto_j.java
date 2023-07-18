package com.example.demo.gonggongdata.abandoned_animal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalSearchListDto_j {

    private String noticeSdt; // 공고 시작일 (검색 시작일 YYYYMMDD)
    private String noticeEdt; // 공고 종료일 (검색 종료일 YYYYMMDD)
    private String upkind; // 축종 코드 (개: 417000, 고양이 422400)
    private String kind; // 품종 코드
    private String upr_cd; //  시도 코드
    private String org_cd; // 시군구 코드
    private String neuter_yn; // 중성화 여부 (예: Y, 아니오: N, 미상: U)
    private String pageNo; // 페이지 번호 (기본값은 1)
    // private String numOfRows; // 페이지당 보여줄 개수 (기본 값은 10, 1,000 이하만 가능)
    private String _type; // 응답 형태 (json)
}
