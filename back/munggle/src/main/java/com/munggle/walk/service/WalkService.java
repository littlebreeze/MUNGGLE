package com.munggle.walk.service;

import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.WalkDto;
import com.munggle.walk.dto.WalkUpdateDto;

import java.util.List;

public interface WalkService {

    // 산책 생성
    void createWalk(WalkDto walkDto);

    // 산책 종료 후 후기 등록
    void updateWalk(Walk walkUpdateDto);

    // 내 기록 목록
    List<WalkDto> readMyWalks(Long userId);

    // 해당일 기록 목록

    // 위치 기반 기록 목록 - 출발점 기준으로 (반경)
    List<WalkDto> readLocationWalks(Float latitude, Float Longitude);

    // 산책 삭제
    void deleteWalk(Long walkId);

    // 산책 상세
    WalkDto detailWalk(Long walkId);

    // 산책 수정
    WalkDto updateWalk(WalkUpdateDto walkUpdateDto);
}
