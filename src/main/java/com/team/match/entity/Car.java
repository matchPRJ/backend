package com.team.match.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;
    private String tag;         // 국산 / 수입 태그
    private String type;        // 차종 Ex. suv, 경차, 대형차 등
    private String brand;       // 브랜드 Ex. 기아, 현대, 벤츠, BMW
    private String name;       // 풀 네임
    private String price;       // 가격  해당 중고차 화면에 표시할 가격
    private Integer pricer;     // 검색 기능 중 가격에 해당
    private Short year;        // 연식
    private String km;          // 주행거리
    private Integer kmr;        // 검색 기능 중 주행거리에 해당
    private String oil;     // 연료 타입
}
