package com.munggle.walk.repository;

import com.munggle.domain.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    // 해당 WalkId에 대한 위치 기록 추가하기
    // WalkId에 따라 리스트 가져오기
    List<Optional<Location>> findAllByWalkWalkId(Long userId);
}
