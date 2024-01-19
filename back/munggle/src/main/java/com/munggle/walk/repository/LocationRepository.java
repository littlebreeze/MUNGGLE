package com.munggle.walk.repository;

import com.munggle.domain.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

    // 해당 WalkId에 대한 위치 기록 추가하기
    // WalkId에 따라 리스트 가져오기
}
