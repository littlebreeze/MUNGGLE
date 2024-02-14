package com.munggle.walk.repository;

import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.WalkDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WalkRepository extends JpaRepository<Walk, Long> {

    // 회원의 전체 산책 목록
    Optional<List<Walk>> findAllByUserIdAndIsDeletedFalse(Long userId);

    // 산책 전체 목록
    Optional<List<Walk>> findAllByIsDeletedFalseAndIsPrivatedFalse();

    // 산책 월별 목록
    Optional<List<Walk>> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    // 산책 상세 정보
    Optional<Walk> findByWalkIdAndIsDeletedFalse(Long walkId);


}
