package com.munggle.post.repository;

import com.munggle.domain.model.entity.PostLike;
import com.munggle.domain.model.entity.PostLikeId;
import com.munggle.post.dto.response.PostListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRespository extends JpaRepository<PostLike, PostLikeId> {

    Boolean existsByPostLikeIdAndIsDeletedFalse(PostLikeId postLikeId);
}
