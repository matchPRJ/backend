package com.team.match.service;

import com.team.match.dto.PageRequestDTO;
import com.team.match.dto.PageResultDTO;
import com.team.match.entity.Board;
import com.team.match.entity.User;
import com.team.match.dto.BoardDTO;

public interface BoardService {

    // 게시글 등록 서비스
    Long register(BoardDTO dto);

    // 게시글 목록 처리 (필터링 미완성)
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 게시글 조회 처리
    BoardDTO get(Long bno);

    // 게시글 삭제 처리
    void remove(BoardDTO dto);

    // 게시글 수정 처리
    void modify(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto) {
        User user = User.builder().uno(dto.getBuno()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .btitle(dto.getBtitle())
                .bcontent(dto.getBcontent())
                .link(dto.getLink())
                .bdelete(dto.isBdelete())
                .buno(user)
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board, User user, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .buno(user.getUno())
                .bnickname(user.getNickname())
                .btitle(board.getBtitle())
                .bcontent(board.getBcontent())
                .link(board.getLink())
                .bdelete(board.isBdelete())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .replyCount(replyCount.intValue())
                .build();
        return boardDTO;
    }
}
