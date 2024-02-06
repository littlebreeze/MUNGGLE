package com.munggle.dog.repository;

import com.munggle.domain.model.entity.MatchingSelection;
import com.munggle.domain.model.entity.MatchingSelectionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchingSelectionRepository extends JpaRepository<MatchingSelection, MatchingSelectionId> {

    Optional<List<MatchingSelection>> findAllById_DogId(Long dogId);
}
