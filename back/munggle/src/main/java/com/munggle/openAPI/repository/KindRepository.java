package com.munggle.openAPI.repository;

import com.munggle.domain.model.entity.Kind;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KindRepository extends JpaRepository<Kind, Long> {

    List<Kind> findByKindNmLike(String input);
}
