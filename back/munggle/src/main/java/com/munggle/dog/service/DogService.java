package com.munggle.dog.service;

import com.munggle.dog.dto.DogCreateDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.dto.DogUpdateDto;

import java.util.List;

public interface DogService {

    void insertDog(DogCreateDto dogCreateDto);

    void updateDog(Long userId, Long dogId, DogUpdateDto dogUpdateDto);

    void deleteDog(Long userId, Long dogId);

    DogDetailDto getDetailDog(Long dogId);

    List<DogDetailDto> getDogList(Long userId);
}
