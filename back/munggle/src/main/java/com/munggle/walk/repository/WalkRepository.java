package com.munggle.walk.repository;

import com.munggle.domain.model.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkRepository extends JpaRepository<Walk, Long> {
}
