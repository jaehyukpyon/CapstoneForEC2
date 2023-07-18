package com.example.demo.gonggongdata.abandoned_animal.mapper;

import com.example.demo.gonggongdata.abandoned_animal.dto.AnimalKindDto_j;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnimalKindMapper_j {

    List<AnimalKindDto_j> getAllAnimalDogKinds();
    List<AnimalKindDto_j> getAllAnimalCatKinds();
}
