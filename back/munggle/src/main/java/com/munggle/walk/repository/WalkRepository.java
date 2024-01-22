package com.munggle.walk.repository;

import com.munggle.domain.model.entity.Walk;
import com.munggle.walk.dto.WalkDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalkRepository extends JpaRepository<Walk, Long> {

    List<Optional<Walk>> findAllByUserId(Long userId);
}
