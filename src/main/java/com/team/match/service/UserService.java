package com.team.match.service;

import com.team.match.dto.UserDTO;
import com.team.match.entity.User;

public interface UserService {

    //로그인
    UserDTO login(UserDTO user);

    //user Entity객체를 userDTO 클래스로 변환
    default UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .uno(user.getUno())
                .id(user.getId())
                .password(user.getPassword())
                .sex(user.getSex())
                .age(user.getAge())
                .name(user.getName())
                .nickname(user.getNickname())
                .build();
    }

}
