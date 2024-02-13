package com.munggle.dog.service;

import com.munggle.dog.dto.DogCharDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.dog.dto.SelectionRequestDto;
import com.munggle.dog.repository.DogRepository;
import com.munggle.dog.repository.MatchingQueryRepository;
import com.munggle.dog.repository.MatchingRepository;
import com.munggle.dog.repository.MatchingSelectionRepository;
import com.munggle.domain.exception.DogNotFoundException;
import com.munggle.domain.exception.ExceptionMessage;
import com.munggle.domain.exception.MatchingCharacterNotFoundException;
import com.munggle.domain.exception.MatchingNotOnException;
import com.munggle.domain.model.entity.Dog;
import com.munggle.domain.model.entity.Matching;
import com.munggle.domain.model.entity.MatchingSelection;
import com.munggle.domain.model.entity.MatchingSelectionId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final DogRepository dogRepository;
    private final MatchingRepository matchingRepository;
    private final MatchingQueryRepository matchingQueryRepository;
    private final MatchingSelectionRepository matchingSelectionRepository;

    @Override
    @Transactional
    public void updateMyDogCharacter(DogCharDto dogCharDto) {
        Dog dog = dogRepository.findByDogIdAndIsDeletedIsFalse(dogCharDto.getDogId())
                .orElseThrow(()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
        dog.updateCharacterId(dogCharDto);
    }

    @Override
    @Transactional
    public void insertMatchingCharacter(DogCharDto dogCharDto) {
        // 내 반려견 찾아와서 매칭 켜기
        Dog dog = dogRepository.findById(dogCharDto.getDogId())
                .orElseThrow(()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
        dog.onMatching();
        Matching matching = Matching.builder()
                .dog(dog)
                .isNeutering(dogCharDto.getIsNeutering())
                .characterId(dogCharDto.toCharacterString())
                .build();
        matchingRepository.save(matching);
    }

    @Override
    @Transactional
    public void updateMatchingCharacter(DogCharDto dogCharDto) {
        Matching matching = matchingRepository.findByDogDogId(dogCharDto.getDogId())
                .orElseThrow(()->new MatchingCharacterNotFoundException(ExceptionMessage.MATCHING_CHARACTER_NOT_FOUND));
        matching.updateMatcing(dogCharDto);
    }

    /*
    * 상위 20개를 하려면 정렬 기준
     */
    @Override
    public List<DogDetailDto> matchingList(Long dogId) {

        // 내 반려견 (특성)
        Dog dog = dogRepository.findByDogIdAndIsDeletedIsFalse(dogId)
                .orElseThrow(()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));

        if(!dog.getIsMatching()){
            throw new MatchingNotOnException(ExceptionMessage.MATCHING_IS_NOT_ON);
        }

        // 매칭에 사용할 특성 리스트
        Matching matching =  matchingRepository.findByDogDogId(dogId)
                .orElseThrow(()->new MatchingCharacterNotFoundException(ExceptionMessage.MATCHING_CHARACTER_NOT_FOUND));

        DogCharDto dogCharDto = DogCharDto.builder()
                .isNeutering(matching.getIsNeutering())
                .characterId(matching.returnCharacterList())
                .build();

        List<Long> selectionList = matchingSelectionRepository.findAllById_DogId(dogId)
                .orElse(null)
                .stream().map(selection -> (Long) selection.getId().getOtherId()).collect(Collectors.toList());

        // 매칭 선별 리스트도 넘겨주기
        return matchingQueryRepository.findDogFriends(dog.getUser(), matching.getIsNeutering(), matching.returnCharacterList(), selectionList);
    }

    @Override
    @Transactional
    public void toggleMatching(Long dogId) {
        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
        if(dog.getIsMatching()){
            dog.offMatching();
        }else{
            dog.onMatching();
        }
    }

    @Override
    public DogCharDto myCharacterList(Long dogId) {

        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(()->new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
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
        Matching matching = matchingRepository.findByDogDogId(dogId)
                .orElseThrow(()->new MatchingCharacterNotFoundException(ExceptionMessage.MATCHING_CHARACTER_NOT_FOUND));
        return DogCharDto.builder()
                .isNeutering(matching.getIsNeutering())
                .characterId(matching.returnCharacterList())
                .build();
    }

    @Override
    public void insertMatchingSelection(SelectionRequestDto selectionRequestDto) {

        List<MatchingSelection> list = new ArrayList<>();

        Dog dog = dogRepository.findByDogIdAndIsDeletedIsFalse(selectionRequestDto.getDogId())
                .orElseThrow(()-> new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));

        for(Long other : selectionRequestDto.getOthersId()){

            // 자기 자신은 넣을 수 없음
            if(selectionRequestDto.getDogId() == other)
                continue;

            Dog otherDog = dogRepository.findByDogIdAndIsDeletedIsFalse(other)
                    .orElseThrow(()-> new DogNotFoundException(ExceptionMessage.DOG_NOT_FOUND));
            MatchingSelectionId id = MatchingSelectionId.builder().dogId(dog.getDogId()).otherId(otherDog.getDogId()).build();
            list.add(MatchingSelection.builder()
                            .id(id)
                            .dog(dog).other(otherDog)
                    .build());
        }

        matchingSelectionRepository.saveAll(list);
    }

    @Override
    public void resetMatchingSelection(Long dogId) {

    }

}
