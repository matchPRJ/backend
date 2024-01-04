package com.team.match.repository;

import com.team.match.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //아이디와 비밀번호 확인
    User findByIdAndPassword(String id, String password);

}
