package com.munggle.dog.service;

import com.munggle.dog.dto.DogCharDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.repository.DogRepository;
import com.munggle.dog.repository.MatchingRepository;
import com.munggle.domain.model.entity.Dog;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final DogRepository dogRepository;
    private final MatchingRepository matchingRepository;

    @Override
    @Transactional
    public void updateMyDogCharacter(Long dogId, DogCharDto dogCharDto) {
        Dog dog = dogRepository.findByIdAndIsDeletedIsFalse(dogId)
                .orElseThrow(()->new NoSuchElementException());
        dog.updateCharacterId(dogCharDto);
    }

    @Override
    public void insertMatchingCharacter(Long dogId, DogCharDto dogCharDto) {

    }

    @Override
    public void updateMatchingCharacter(Long dogId, DogCharDto dogCharDto) {

    }

    @Override
    public List<DogDetailDto> matchingList(Long dogId) {
        return null;
    }
}
