package com.example.demo.repository;

import com.example.demo.domain.Found;
import com.example.demo.dto.FoundDto;
import com.example.demo.mapper.FoundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FoundRepository {

    private final FoundMapper foundMapper;

    public Found saveFound(Found found){
        foundMapper.saveFound(found);
        return found;
    }


    public FoundDto updateFound(int foundId, FoundDto foundDto){
        foundMapper.updateFound(foundId,foundDto);
        return foundDto;
    }


    public void deleteFound(int foundId){
        foundMapper.deleteFound(foundId);

    }

}
