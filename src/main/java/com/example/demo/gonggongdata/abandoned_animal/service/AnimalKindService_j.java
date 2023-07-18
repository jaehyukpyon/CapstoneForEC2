package com.example.demo.gonggongdata.abandoned_animal.service;

import com.example.demo.gonggongdata.abandoned_animal.dto.AnimalKindDto_j;
import com.example.demo.gonggongdata.abandoned_animal.mapper.AnimalKindMapper_j;
import com.example.demo.gonggongdata.abandoned_animal.response.AnimalKindResponse_j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalKindService_j {

    private final AnimalKindMapper_j animalKindMapper;

    public AnimalKindResponse_j getAnimalKindList(String dogOrCat) {
        List<AnimalKindDto_j> kinds;
        if (dogOrCat.equals("개")) {
            kinds = animalKindMapper.getAllAnimalDogKinds();
        } else {
            kinds = animalKindMapper.getAllAnimalCatKinds();
        }
        List<String> toStringList = new ArrayList<>();
        kinds.stream().forEach(kind -> toStringList.add(kind.getKnm()));
        AnimalKindResponse_j response = new AnimalKindResponse_j();
        response.setKinds(toStringList);
        response.setTotalCount(toStringList.size());

        return response;
    }
}
