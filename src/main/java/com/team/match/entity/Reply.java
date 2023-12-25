package com.team.match.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Table(name = "Reply")
public class Reply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String rcontent; // 댓글 내용
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rbno")
    private Board rbno; // 댓글이 작성된 게시글 번호
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "runo")
    private User runo;
    private boolean rdelete;
    public void changeContent(String rcontent) { this.rcontent = rcontent; }
    public void changeDelete(boolean rdelete) { this.rdelete = rdelete; }
}
