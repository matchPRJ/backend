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
        IntStream.rangeClosed(1,8).forEach(i -> {
            User user = User.builder()
                    .nickname("user"+i)
                    .build();

            userRepository.save(user);
        });
    }
}
