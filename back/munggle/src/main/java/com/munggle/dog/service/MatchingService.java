package com.munggle.dog.service;

import com.munggle.dog.dto.DogCharDto;
import com.munggle.dog.dto.DogDetailDto;
import com.munggle.domain.model.entity.Matching;

import java.util.List;

public interface MatchingService {

    // 내 반려견 특징 수정
    void updateMyDogCharacter(DogCharDto dogCharDto);

    // 상대 반려견 특징 생성
    void insertMatchingCharacter(DogCharDto dogCharDto);

    // 상대 반려견 특징 수정
    void updateMatchingCharacter(DogCharDto dogCharDto);

    // 매칭된 반려견 리스트
    List<DogDetailDto> matchingList(Long dogId);

    // 매칭 온오프
    void toggleMatching(Long dogId);

    // 내 반려견 특징
    DogCharDto myCharacterList(Long dogId);

    // 상대 반려견 특징
    DogCharDto matchingCharaterList(Long dogId);

    // 상대 반려견 선별 내용 저장 (수락/거절 상관 없이)
    void insertMatchingSelection(Long dogId, Long[] selections);

    // 선별 내용 초기화 (특성 수정시 or 목록 초기화 시) - 서비스 단위로만 만들어 둠
    void resetMatchingSelection(Long dogId);
}
