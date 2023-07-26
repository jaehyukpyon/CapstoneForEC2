package com.example.demo.controller;

import com.example.demo.dto.FindDto;
import com.example.demo.mapper.FindMapper;
import com.example.demo.service.FindService;
import com.example.demo.domain.Find;
import com.example.demo.vo.AuthUserVo_j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.engine.AttributeName;
import com.example.demo.vo.FindVO;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/find")
public class FindController {
    private final FindService findService;

    @PostMapping("/save")
    public Find saveFind(@RequestBody Find find){
        findService.saveFind(find);
        return find;
    }

    @GetMapping("")
    FindDto getFindDto(@PathVariable int findId) {
        FindDto findDto= findService.getFindDto(findId);
        return findDto;
    }
    @GetMapping("/list")
    public List<FindVO> getList(){
        List<FindVO> list = findService.getList();
        return list;
    }

    @PostMapping("/{findId}/edit")
    public FindDto updateFind(@PathVariable int findId, @RequestBody FindDto findDto) {
        FindDto check = getFindDto(findId);
        AuthUserVo_j auth = (AuthUserVo_j) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = auth.getId();
        if(id == check.getUserId()){
            findService.updateFind(findId, findDto);
            return findDto;
        }
        else {
            System.out.println("Invalid User");
            return null;
        }
    }

    @DeleteMapping("{findId}/delete")
    public void deleteFind(@PathVariable int findId){
        FindDto check = getFindDto(findId);
        AuthUserVo_j auth = (AuthUserVo_j) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = auth.getId();
        if(id == check.getUserId()){
            findService.deleteFind(findId);
        }
        else {
            System.out.println("Invalid User");
        }

    }


}
