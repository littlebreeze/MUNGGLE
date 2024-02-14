package com.munggle.walk.service;

import com.munggle.domain.model.entity.User;
import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.WalkCalendarDto;
import com.munggle.walk.dto.WalkCreateDto;
import com.munggle.walk.dto.WalkDto;
import com.munggle.walk.dto.WalkUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WalkService {

    // 산책 생성
    Long createWalk(WalkCreateDto walkDto);

    void updateWalkImage(Long walkId, MultipartFile file);

    // 내 기록 목록
    WalkCalendarDto readMyWalks(Long userId, Integer year, Integer month);

    // 해당일 기록 목록

    // 위치 기반 기록 목록 - 출발점 기준으로 (반경)
    List<WalkDto> readLocationWalks(Float latitude, Float Longitude);

    // 산책 삭제
    void deleteWalk(Long walkId);

    // 산책 상세
    WalkDto detailWalk(Long walkId);

    // 산책 종료 후 후기 등록, 산책 수정
    WalkDto updateWalk(WalkUpdateDto walkUpdateDto, Long userId);

    // 산책 공개여부 변경
    void toggleVisibility(Long walkId);
}
