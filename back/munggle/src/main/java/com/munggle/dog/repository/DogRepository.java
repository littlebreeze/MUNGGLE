package com.munggle.dog.repository;

import com.munggle.domain.model.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface DogRepository extends JpaRepository<Dog, Long> {

    Optional<Dog> findByIdAndIsDeletedIsFalse(Long dogId);

    Optional<List<Dog>> findAllByUserIdAndIsDeletedIsFalse(Long userId);
}
