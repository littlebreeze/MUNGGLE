package com.munggle.dog.repository;

import com.munggle.domain.model.entity.MatchingSelection;
import com.munggle.domain.model.entity.MatchingSelectionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingSelectionRepository extends JpaRepository<MatchingSelection, MatchingSelectionId> {


}
