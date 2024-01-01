package com.team.match.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.team.match.entity.User;

import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertUsers() {
        IntStream.rangeClosed(2,3).forEach(i -> {
            User user = User.builder()
                    .id("user"+i)
                    .password("user"+i)
                    .sex("여")
                    .age(24)
                    .name("human"+i)
                    .nickname("user"+i)
                    .build();

            userRepository.save(user);
        });
    }
}
