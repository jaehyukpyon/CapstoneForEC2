package com.example.demo.gonggongdata.abandoned_animal.controller;

import com.example.demo.gonggongdata.abandoned_animal.response.AnimalKindResponse_j;
import com.example.demo.gonggongdata.abandoned_animal.service.AnimalKindService_j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/noauth")
public class AnimalKindController_j {

    private final AnimalKindService_j animalKindService;

    @GetMapping("/animalkind-list")
    public ResponseEntity<AnimalKindResponse_j> getAnimalKind(@RequestParam String dogOrCat) {
        AnimalKindResponse_j response;

        if (dogOrCat.equals("개")) {
            response = animalKindService.getAnimalKindList("개");
        } else {
            response = animalKindService.getAnimalKindList("고양이");
        }

        return ResponseEntity.ok(response);
    }
}
