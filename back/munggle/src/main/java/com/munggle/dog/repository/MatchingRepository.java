package com.munggle.dog.repository;

import com.munggle.domain.model.entity.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
}
