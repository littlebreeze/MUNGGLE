package com.munggle.openAPI.repository;

import com.munggle.domain.model.entity.LostDog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostDogRepository extends JpaRepository<LostDog, Integer> {
}
