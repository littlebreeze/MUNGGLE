package com.munggle.openAPI.repository;

import com.munggle.domain.model.entity.LostDog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LostDogRepository extends JpaRepository<LostDog, Long> {

    Optional<List<LostDog>> findByCareAddrLikeAndKindLike(String careAddr, String kind);
}
