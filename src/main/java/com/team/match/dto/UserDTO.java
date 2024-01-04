package com.team.match.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long uno;
    private String id;
    private String password;
    private String sex;
    private Integer age;
    private String name;
    private String nickname;
}
