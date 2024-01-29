package com.munggle.post.repository;

import com.munggle.domain.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagNm(String hashtag);
}
