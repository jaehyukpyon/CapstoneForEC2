package com.example.demo.controller;

import com.example.demo.domain.Found;
import com.example.demo.dto.FoundDto;
import com.example.demo.service.FoundService;
import com.example.demo.vo.AuthUserVo_j;
import com.example.demo.vo.FoundVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/found")
public class FoundController {
    private final FoundService foundService;

    @PostMapping("/save")
    public Found saveFound(@RequestBody Found found){
        foundService.saveFound(found);
        return found;
    }
    @GetMapping("")
    FoundDto getFoundDto(@PathVariable int foundId) {
        FoundDto foundDto= foundService.getFoundDto(foundId);
        return foundDto;
    }
    @PostMapping("/{foundId}/edit")
    public FoundDto updateFound(@PathVariable int foundId, @RequestBody FoundDto foundDto) {
        FoundDto check = getFoundDto(foundId);
        AuthUserVo_j auth = (AuthUserVo_j) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = auth.getId();
        if(id == check.getUserId()){
            foundService.updateFound(foundId, foundDto);
            return foundDto;
        }
        else {
            System.out.println("Invalid User");
            return null;
        }
    }

    @DeleteMapping("{foundId}/delete")
    public void deleteFound(@PathVariable int foundId){

        FoundDto check = getFoundDto(foundId);
        AuthUserVo_j auth = (AuthUserVo_j) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = auth.getId();
        if(id == check.getUserId()){
            foundService.deleteFound(foundId);
        }
        else {
            System.out.println("Invalid User");
        }
    }
    @GetMapping("/list")
    public List<FoundVO> getList(){
        List<FoundVO> list = foundService.getList();
        return list;
    }

}
