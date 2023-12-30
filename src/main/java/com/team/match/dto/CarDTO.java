package com.team.match.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDTO {
    private Long cno;
    private String tag;
    private String type;
    private String brand;
    private String name;
    private String price;
    private Integer pricer;
    private Short year;
    private String km;
    private Integer kmr;
    private String oil;
}
