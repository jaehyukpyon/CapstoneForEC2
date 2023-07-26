package com.example.demo.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.demo.dto.FindDto;
import com.example.demo.repository.FindRepository;
import com.example.demo.domain.Find;
import com.example.demo.vo.AuthUserVo_j;
import com.example.demo.vo.FindVO;
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
public class FindService {

    private final FindRepository findRepository;

    public Find saveFind(Find find){
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        find.setRegisterAt(now);
        AuthUserVo_j auth = (AuthUserVo_j) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = auth.getId();
        find.setUserId(id);
        findRepository.saveFind(find);
        return find;
    }

    public FindDto updateFind(int findId, FindDto findDto){

        //Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        //findDto.setUpdatedAt(now);
        findDto.setFindId(findId);
        AuthUserVo_j auth = (AuthUserVo_j) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = auth.getId();
        findDto.setUserId(id);
        findRepository.updateFind(findId, findDto);
        return findDto;
    }

    public void deleteFind(int findId){
        findRepository.deleteFind(findId);
    }

    public FindDto getFindDto(int findId) {
        FindDto findDto = findRepository.getFindDto(findId);
        return findDto;
    }
    public List<FindVO> getList(){
        List<FindVO> list = findRepository.getList();
        return list;
    }
}
