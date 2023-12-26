package com.team.match.repository;

import com.team.match.entity.Reply;
import com.team.match.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface ReplyRepository extends JpaRepository<Reply, Long>{

    // 게시글 삭제 시 해당 게시글 댓글들 상태 false로 삭제 처리
    @Modifying
    @Query("update Reply r set r.rdelete = false where r.rbno.bno =:bno")
    void deleteByBno(Long bno);

    // rno 기준으로 rdelete값이 true에 해당하는 댓글들을 가져온다.
    @Query("select r from Reply r where r.rbno = :rbno and r.rdelete = true order by r.rno")
    List<Reply> getReply(Board rbno);
}
