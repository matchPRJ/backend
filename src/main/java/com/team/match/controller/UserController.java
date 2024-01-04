package com.team.match.controller;

import com.team.match.dto.UserDTO;
import com.team.match.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 컨트롤러", description = "사용자 관련 api")
@RestController
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    UserService userService;

    //로그인
    @PostMapping("/login")
    @Operation(summary = "로그인 컨트롤러", description = "아이디와 비밀번호로 로그인")
    public UserDTO login(@RequestBody UserDTO user){
        return userService.login(user);
    }

}
