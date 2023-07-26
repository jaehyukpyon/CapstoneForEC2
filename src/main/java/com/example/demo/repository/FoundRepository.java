package com.example.demo.repository;

import com.example.demo.domain.Found;
import com.example.demo.dto.FoundDto;
import com.example.demo.mapper.FoundMapper;
import com.example.demo.vo.FoundVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public FoundDto getFoundDto(int foundId) {
        FoundDto foundDto = foundMapper.getFoundDto(foundId);
        return foundDto;
    }

    public List<FoundVO> getList() {
        List<FoundVO> list = foundMapper.getList();
        return list;
    }
}
