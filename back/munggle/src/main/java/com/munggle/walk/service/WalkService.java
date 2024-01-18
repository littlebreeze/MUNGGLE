package com.munggle.walk.service;

import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.WalkCreateDto;
import com.munggle.walk.dto.WalkUpdateDto;

import java.util.List;

public interface WalkService {

    // 산책 생성
    void createWalk(WalkCreateDto walkCreateDto);

    // 산책 종료 후 후기 등록
    void updateWalk(WalkUpdateDto walkUpdateDto);

    // 내 기록 목록
    List<Walk> readMyWalks(String userId);

    // 해당일 기록 목록

    // 위치 기반 기록 목록 - 출발점 기준으로
    List<Walk> readLocationWalks(Float latitude, Float Longitude);

    // 산책 삭제
    void deleteWalk(Long walkId);

    // 산책 상세
    void detailWalk(Long walkId);
}
