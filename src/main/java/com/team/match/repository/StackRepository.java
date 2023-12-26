package com.team.match.repository;

import com.team.match.entity.Board;
import com.team.match.entity.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StackRepository extends JpaRepository<Stack, Long> {

    @Modifying
    @Query("update Stack s set s.sdelete = false where s.sbno.bno =:bno")
    void deleteByBno(Long bno);

    @Query("select s from Stack s where s.sbno =:sbno and s.sdelete = true order by s.sno")
    List<Stack> getStack(Board sbno);
}
