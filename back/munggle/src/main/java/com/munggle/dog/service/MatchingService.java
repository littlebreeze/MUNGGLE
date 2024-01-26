package com.munggle.dog.service;

import com.munggle.dog.dto.DogCharDto;
import com.munggle.dog.dto.DogDetailDto;

import java.util.List;

public interface MatchingService {

    // 내 반려견 특징 수정
    void updateMyDogCharacter(Long dogId, DogCharDto dogCharDto);

    // 상대 반려견 특징 생성
    void insertMatchingCharacter(Long dogId, DogCharDto dogCharDto);

    // 상대 반려견 특징 수정
    void updateMatchingCharacter(Long dogId, DogCharDto dogCharDto);

    // 매칭된 반려견 리스트
    List<DogDetailDto> matchingList(Long dogId);
}
