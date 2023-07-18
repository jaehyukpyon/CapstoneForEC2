package com.example.demo.repository;

import com.example.demo.dto.FindDto;
import com.example.demo.mapper.FindMapper;
import com.example.demo.domain.Find;
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

}
