package com.munggle.dog.service;

import com.munggle.dog.dto.DogDto;
import com.munggle.dog.mapper.DogMapper;
import com.munggle.dog.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    @Override
    public void insertDog(DogDto dogDto) {
        dogRepository.save(DogMapper.toEntity(dogDto));
    }
}
