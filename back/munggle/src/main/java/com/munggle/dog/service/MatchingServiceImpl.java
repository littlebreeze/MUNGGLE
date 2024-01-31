package com.munggle.dog.service;

import com.munggle.dog.dto.DogCharDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.mapper.DogMapper;
import com.munggle.dog.repository.DogRepository;
import com.munggle.dog.repository.MatchingQueryRepository;
import com.munggle.dog.repository.MatchingRepository;
import com.munggle.domain.model.entity.Dog;
import com.munggle.domain.model.entity.Matching;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final DogRepository dogRepository;
    private final MatchingRepository matchingRepository;
    private final MatchingQueryRepository matchingQueryRepository;

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

    /*
    * 여기까지 했을 때 정렬...은?
    * 상위 20개를 하려면 정렬 기준이 필요한데,,,
    * 선택/넘긴 멍멍이들도 관리가 되어야함.
     */
    @Override
    public List<DogDetailDto> matchingList(Long dogId) {

        // 매칭에 사용할 특성 리스트
        Matching matching =  matchingRepository.findByDogDogId(dogId).orElseThrow(()->new NoSuchElementException());
        DogCharDto dogCharDto = DogCharDto.builder()
                .isNeutering(matching.getIsNeutering())
                .characterId(matching.returnCharacterList())
                .build();

        // 내 반려견 (특성)
        Dog dog = dogRepository.findByDogIdAndIsDeletedIsFalse(dogId).orElseThrow();

        return matchingQueryRepository.findDogFriends(dog.getUser(), matching.getIsNeutering(), matching.returnCharacterList());
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

    @Override
    public DogCharDto myCharacterList(Long dogId) {

        Dog dog = dogRepository.findById(dogId).orElseThrow(()-> new NoSuchElementException());
        Matching matching = Matching.builder()
                .isNeutering(dog.getIsNeutering())
                .characterId(dog.getCharacterId())
                .build();
        return DogCharDto.builder()
                .isNeutering(matching.getIsNeutering())
                .characterId(matching.returnCharacterList())
                .build();
    }

    @Override
    public DogCharDto matchingCharaterList(Long dogId) {
        Matching matching = matchingRepository.findByDogDogId(dogId).orElseThrow(()-> new NoSuchElementException());
        return DogCharDto.builder()
                .isNeutering(matching.getIsNeutering())
                .characterId(matching.returnCharacterList())
                .build();
    }
}
