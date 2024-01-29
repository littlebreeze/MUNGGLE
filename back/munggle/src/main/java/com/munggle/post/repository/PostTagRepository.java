package com.munggle.post.repository;

import com.munggle.domain.model.entity.PostTag;
import com.munggle.domain.model.entity.PostTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, PostTagId> {
}
