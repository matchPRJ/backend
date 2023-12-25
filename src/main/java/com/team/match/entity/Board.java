package com.team.match.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String btitle;
    private String bcontent;
    private boolean bdelete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buno")
    private User buno;

    public void changeTitle(String btitle) {
        this.btitle = btitle;
    }
    public void changeContent(String bcontent) {
        this.bcontent = bcontent;
    }
    public void changeDelete(boolean bdelete) {
        this.bdelete = bdelete;
    }
}
