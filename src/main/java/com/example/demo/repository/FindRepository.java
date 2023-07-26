package com.example.demo.repository;

import com.example.demo.dto.FindDto;
import com.example.demo.mapper.FindMapper;
import com.example.demo.domain.Find;
import com.example.demo.vo.FindVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FindRepository{

    private final FindMapper findMapper;

    public Find saveFind(Find find){
        findMapper.saveFind(find);
        return find;
    }


    public FindDto updateFind(int findId, FindDto findDto){
        findMapper.updateFind(findId,findDto);
        return findDto;
    }


    public void deleteFind(int findId){
        findMapper.deleteFind(findId);

    }

    public FindDto getFindDto(int findId) {
        FindDto findDto = findMapper.getFindDto(findId);
        return findDto;
    }

    public List<FindVO> getList(){
        List<FindVO> list = findMapper.getList();
        return list;
    }
}
