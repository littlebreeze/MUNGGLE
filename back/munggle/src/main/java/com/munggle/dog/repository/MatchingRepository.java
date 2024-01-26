package com.munggle.dog.repository;

import com.munggle.domain.model.entity.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchingRepository extends JpaRepository<Matching, Long> {

    Optional<Matching> findByDogDogId(Long dogId);
}
