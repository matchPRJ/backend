package com.team.match.dto;

import lombok.*;
import org.springframework.data.domain.*;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    private int page;
    private int size;
    // 게시판 검색
    private String btype;

    // 중고차 가격 비교 검색
    private String nType; // 이름
    private String tType; // 태그
    private String typeType; //차종
    private String brandType; // 브랜드
    private String oilType; // 연료
    private String kType;     // km 범위
    private String yType;     // 연식 범위

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page -1, size, sort);
    }

}
