package com.team.match.dto;

import lombok.*;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StackDTO {
    private Long sno;
    private String stackName;
    private Long suno;

}
