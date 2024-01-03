package com.team.match.controller;

import com.team.match.dto.UserDTO;
import com.team.match.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    UserService userService;

    //로그인
    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO user){
        return userService.login(user);
    }

}
