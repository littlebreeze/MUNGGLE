package com.munggle.dog.service;

import com.munggle.dog.dto.DogCreateDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.dto.DogUpdateDto;
import com.munggle.dog.mapper.DogMapper;
import com.munggle.dog.repository.DogRepository;
import com.munggle.domain.model.entity.Dog;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    @Override
    public void insertDog(DogCreateDto dogCreateDto) {
        dogRepository.save(DogMapper.toEntity(dogCreateDto));
    }

    @Override
    @Transactional
    public void updateDog(Long dogId, DogUpdateDto dogUpdateDto) {
        Dog dog = dogRepository.findByIdAndIsDeletedIsFalse(dogId)
                .orElseThrow(()->new NoSuchElementException());
        dog.updateDog(dogUpdateDto);
    }

    @Override
    @Transactional
    public void deleteDog(Long dogId) {
        Dog dog = dogRepository.findByIdAndIsDeletedIsFalse(dogId)
                .orElseThrow(()->new NoSuchElementException());
        dog.deleteDog();
    }

    @Override
    public DogDetailDto getDetailDog(Long dogId) {
        Dog dog = dogRepository.findByIdAndIsDeletedIsFalse(dogId)
                .orElseThrow(()->new NoSuchElementException());
        return DogMapper.toDetailDto(dog);
    }

    @Override
    public List<DogDetailDto> getDogList(Long userId) {
        List<Dog> list = dogRepository.findAllByUserIdAndIsDeletedIsFalse(userId)
                .orElseThrow(()->new NoSuchElementException());
        return list.stream().map(dog -> DogMapper.toDetailDto(dog)).collect(Collectors.toList());
    }
}
