package com.team.match.service;

import com.team.match.dto.UserDTO;
import com.team.match.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    //로그인
    @Override
    public UserDTO login(UserDTO user){
        if(userRepository.findByIdAndPassword(user.getId(), user.getPassword()) != null) {
            return toUserDTO(userRepository.findByIdAndPassword(user.getId(), user.getPassword()));
        }else{
            return new UserDTO();
        }
    }
}
