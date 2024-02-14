package com.munggle.dog.service;

import com.munggle.dog.dto.DogCreateDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.dto.DogUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DogService {

    Long insertDog(DogCreateDto dogCreateDto);

    void updateDog(Long userId, Long dogId, DogUpdateDto dogUpdateDto);

    void updateDogImage(Long userId, Long dogId, MultipartFile file);

    void deleteDog(Long userId, Long dogId);

    DogDetailDto getDetailDog(Long dogId);

    List<DogDetailDto> getDogList(Long userId);
}
