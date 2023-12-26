package com.team.match.service;

import com.team.match.dto.PageRequestDTO;
import com.team.match.dto.PageResultDTO;
import com.team.match.dto.BoardDTO;
import com.team.match.entity.Board;
import com.team.match.entity.User;
import com.team.match.repository.BoardRepository;
import com.team.match.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    // 게시글 등록 처리
    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = dtoToEntity(boardDTO);
        boardRepository.save(board);
        return board.getBno();
    }

    // 게시글 목록 처리
//    @Override
//    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
//        log.info(pageRequestDTO);
//
//    }

    // 게시글 조회 서비스
    @Override
    public BoardDTO get(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[])result;
        return entityToDTO((Board)arr[0], (User)arr[1], (Long)arr[2]);
    }

    // 게시글 삭제 처리
    @Transactional
    @Override
    public void remove(BoardDTO boardDTO) {
        Board board = boardRepository.getReferenceById(boardDTO.getBno());

        if(boardDTO.isBdelete() == true) {
            board.changeDelete(false);
            replyRepository.deleteByBno(boardDTO.getBno());
            boardRepository.save(board);
        }
    }

    // 게시글 수정 처리
    @Transactional
    @Override
    public void modify(BoardDTO dto) {
        Board board = boardRepository.getReferenceById(dto.getBno());

        if(dto.getBtitle() != null) {
            board.changeTitle(dto.getBtitle());
        }
        if(dto.getBcontent() != null) {
            board.changeContent(dto.getBcontent());
        }
        boardRepository.save(board);
    }
}
