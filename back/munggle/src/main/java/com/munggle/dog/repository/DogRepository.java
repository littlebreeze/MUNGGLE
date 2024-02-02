package com.munggle.dog.repository;

import com.munggle.domain.model.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface DogRepository extends JpaRepository<Dog, Long> {

    Optional<Dog> findByDogIdAndIsDeletedIsFalse(Long dogId);

    Optional<List<Dog>> findAllByUserIdAndIsDeletedIsFalse(Long userId);

    // 내 반려견이 아니고, 매칭이 켜져있는 반려견만
    //Optional<List<Dog>> findAllByUserIdIsNotAndIsMatchingIsTrue(Long userId);

    Optional<List<Dog>> findAllByUserIdIsNotAndIsMatchingIsTrueAndCharacterIdLike(Long userId, String str);
}
