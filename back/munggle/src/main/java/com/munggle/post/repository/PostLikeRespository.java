package com.munggle.post.repository;

import com.munggle.domain.model.entity.PostLike;
import com.munggle.domain.model.entity.PostLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRespository extends JpaRepository<PostLike, PostLikeId> {

}
