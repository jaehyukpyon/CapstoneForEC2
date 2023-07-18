package com.example.demo.controller;

import com.example.demo.domain.Found;
import com.example.demo.dto.FoundDto;
import com.example.demo.service.FoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{foundId}/edit")
    public FoundDto updateFound(@PathVariable int foundId, @RequestBody FoundDto foundDto) {
        foundService.updateFound(foundId, foundDto);
        return foundDto;
    }

    @DeleteMapping("{foundId}/delete")
    public void deleteFound(@PathVariable int foundId){
        foundService.deleteFound(foundId);
    }
    

}
