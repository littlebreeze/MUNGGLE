package com.munggle.openAPI.repository;

import com.munggle.domain.model.entity.Kind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KindRepository extends JpaRepository<Kind, Integer> {
}
