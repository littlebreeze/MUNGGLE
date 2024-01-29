package com.munggle.dog.service;

import com.munggle.dog.dto.DogCharDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.repository.DogRepository;
import com.munggle.dog.repository.MatchingRepository;
import com.munggle.domain.model.entity.Dog;
import com.munggle.domain.model.entity.Matching;
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
        Dog dog = dogRepository.findByDogIdAndIsDeletedIsFalse(dogId)
                .orElseThrow(()->new NoSuchElementException());
        dog.updateCharacterId(dogCharDto);
    }

    @Override
    @Transactional
    public void insertMatchingCharacter(Long dogId, DogCharDto dogCharDto) {
        // 내 반려견 찾아와서 매칭 켜기
        Dog dog = dogRepository.findById(dogId).orElseThrow(()-> new NoSuchElementException());
        dog.onMatching();
        Matching matching = Matching.builder()
                .dog(Dog.builder().dogId(dogId).build())
                .isNeutering(dogCharDto.getIsNeutering())
                .characterId(dogCharDto.toCharacterString())
                .build();
        matchingRepository.save(matching);
    }

    @Override
    @Transactional
    public void updateMatchingCharacter(Long dogId, DogCharDto dogCharDto) {
        Matching matching = matchingRepository.findByDogDogId(dogId).orElseThrow(()->new NoSuchElementException());
        matching.updateMatcing(dogCharDto);
    }

    @Override
    public List<DogDetailDto> matchingList(Long dogId) {
        return null;
    }

    @Override
    @Transactional
    public void toggleMatching(Long dogId) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(()-> new NoSuchElementException());
        if(dog.getIsMatching()){
            dog.offMatching();
        }else{
            dog.onMatching();
        }
    }
}
