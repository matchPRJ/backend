package com.team.match.dto;

import lombok.*;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyDTO {

    private Long rno;
    private Long runo;
    private String rnickname;
    private Long rbno;
    private String rcontent;
    private LocalDate regDate;
    private LocalDate modDate;

    // bdelete값의 초기값을 true로 설정
    // true = 글이 존재한다, false = 글이 존재하지 않는다.
    @Builder.Default
    private boolean rdelete = true;
}
