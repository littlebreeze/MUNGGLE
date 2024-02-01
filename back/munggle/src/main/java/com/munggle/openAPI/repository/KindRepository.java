package com.munggle.openAPI.repository;

import com.munggle.domain.model.entity.Kind;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KindRepository extends JpaRepository<Kind, Long> {

    Optional<List<Kind>> findByKindNmContains(String input);
}
