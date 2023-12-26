package com.team.match.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Table(name = "Stack")
public class Stack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;
    private String stackName;
    private boolean sdelete;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sbno")
    private Board sbno;
}
