package com.munggle.walk.repository;

import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.WalkDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalkRepository extends JpaRepository<Walk, Long> {

    // 회원의 전체 산책 목록
    List<Optional<Walk>> findAllByUserIdAndIsDeletedFalse(Long userId);

    // 산책 상세 정보
    Optional<Walk> findByWalkIdAndIsDeletedFalse(Long walkId);


}
