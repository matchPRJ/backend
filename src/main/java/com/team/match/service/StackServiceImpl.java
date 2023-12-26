package com.team.match.service;

import com.team.match.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.team.match.dto.StackDTO;
import com.team.match.repository.StackRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class StackServiceImpl implements StackService {

    private final StackRepository repository;

    // 기술 스택 등록 처리
    @Override
    public Long register(StackDTO dto) {
        Stack stack = dtoToEntity(dto);
        repository.save(stack);
        return stack.getSno();
    }

    @Override
    public List<StackDTO> getList(Long sno) {
        List<Stack> result = repository.getStack(Board.builder().bno(sno).build());
        return result.stream().map(stack -> entityToDTO(stack)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void modify(List<StackDTO> newStackDTOList, Long sbno) {
        repository.deleteByBno(sbno);

        for(StackDTO newStackDTO : newStackDTOList) {
            Long sno = register(newStackDTO);
            log.info("New data added with sno: " + sno);
        }
    }
}
