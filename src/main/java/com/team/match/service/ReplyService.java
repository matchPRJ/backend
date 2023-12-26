package com.team.match.service;


import com.team.match.dto.ReplyDTO;
import com.team.match.entity.Board;
import com.team.match.entity.Reply;
import com.team.match.entity.User;

import java.util.List;

public interface ReplyService {

    // 댓글 등록 처리
    Long register(ReplyDTO replyDTO);

    // 댓글 조회 처리
    List<ReplyDTO> getList(Long bno);

    // 댓글 수정 처리
    void modify(ReplyDTO replyDTO);

    // 댓글 삭제 처리
    void remove(ReplyDTO replyDTO);

    default Reply dtoToEntity(ReplyDTO replyDTO) {
        Board board = Board.builder().bno(replyDTO.getRbno()).build();
        User user = User.builder().uno(replyDTO.getRuno()).build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .rcontent(replyDTO.getRcontent())
                .runo(user)
                .rbno(board)
                .rdelete(replyDTO.isRdelete())
                .build();
        return reply;
    }

    default ReplyDTO entityToDTO(Reply reply, User user) {
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .rcontent(reply.getRcontent())
                .rdelete(reply.isRdelete())
                .rnickname(user.getNickname())
                .runo(reply.getRuno().getUno())
                .rbno(reply.getRbno().getBno())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return dto;
    }
}
