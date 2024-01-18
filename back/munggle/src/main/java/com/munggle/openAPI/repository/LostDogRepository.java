package com.munggle.openAPI.repository;

import com.munggle.domain.model.entity.LostDog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostDogRepository extends JpaRepository<LostDog, Long> {

    List<LostDog> findByCareAddrLikeAndKindLike(String careAddr, String kind);
}
