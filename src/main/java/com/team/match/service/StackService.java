package com.team.match.service;

import com.team.match.entity.*;
import com.team.match.dto.*;
import java.util.List;

public interface StackService {

    Long register(StackDTO dto);

    List<StackDTO> getList(Long sno);

    void modify(List<StackDTO> newStackDTOList, Long sbno);

    default Stack dtoToEntity(StackDTO dto) {
        Board board = Board.builder().bno(dto.getSbno()).build();

        Stack stack = Stack.builder()
                .sno(dto.getSno())
                .sbno(board)
                .stackName(dto.getStackName())
                .sdelete(dto.isSdelete())
                .build();
        return stack;
    }

    default StackDTO entityToDTO(Stack stack) {

       StackDTO stackDTO = StackDTO.builder()
               .sno(stack.getSno())
               .stackName(stack.getStackName())
               .sdelete(stack.isSdelete())
               .build();
       return stackDTO;
    }
}
