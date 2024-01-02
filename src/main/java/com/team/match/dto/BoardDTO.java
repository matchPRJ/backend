package com.team.match.dto;

import lombok.*;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
    private Long bno;
    private Long buno;
    private String bnickname;
    private String btitle;
    private String bcontent;
    private String link;
    private LocalDate regDate;
    private LocalDate modDate;
    private int replyCount;

    // bdelete값의 초기값을 true로 설정
    // true = 글이 존재한다, false = 글이 존재하지 않는다.
    @Builder.Default
    private boolean bdelete = true;

}
