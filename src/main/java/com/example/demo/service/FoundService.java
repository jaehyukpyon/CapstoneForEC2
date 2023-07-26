package com.example.demo.service;
import com.example.demo.domain.Found;
import com.example.demo.dto.FindDto;
import com.example.demo.dto.FoundDto;
import com.example.demo.repository.FoundRepository;
import com.example.demo.vo.AuthUserVo_j;
import com.example.demo.vo.FindVO;
import com.example.demo.vo.FoundVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FoundService {

    private final FoundRepository foundRepository;

    public Found saveFound(Found found){
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        found.setRegisterAt(now);
        AuthUserVo_j auth = (AuthUserVo_j) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = auth.getId();
        found.setUserId(id);
        foundRepository.saveFound(found);
        return found;
    }

    public FoundDto updateFound(int foundId, FoundDto foundDto){

        //Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        //foundDto.setUpdatedAt(now);
        foundDto.setFoundId(foundId);
        AuthUserVo_j auth = (AuthUserVo_j) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = auth.getId();
        foundDto.setUserId(id);
        foundRepository.updateFound(foundId, foundDto);
        return foundDto;
    }

    public void deleteFound(int foundId){
        foundRepository.deleteFound(foundId);
    }

    public FoundDto getFoundDto(int foundId) {
            FoundDto foundDto = foundRepository.getFoundDto(foundId);
            return foundDto;
    }

    public List<FoundVO> getList() {
        List<FoundVO> list = foundRepository.getList();
        return list;
    }
}

