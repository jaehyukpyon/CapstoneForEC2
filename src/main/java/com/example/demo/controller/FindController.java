package com.example.demo.controller;

import com.example.demo.dto.FindDto;
import com.example.demo.service.FindService;
import com.example.demo.domain.Find;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{findId}/edit")
    public FindDto updateFind(@PathVariable int findId, @RequestBody FindDto findDto) {
        findService.updateFind(findId, findDto);
        return findDto;
    }

    @DeleteMapping("{findId}/delete")
    public void deleteFind(@PathVariable int findId){
        findService.deleteFind(findId);
    }


}
