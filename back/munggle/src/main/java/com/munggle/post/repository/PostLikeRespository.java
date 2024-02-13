package com.munggle.post.repository;

import com.munggle.domain.model.entity.Post;
import com.munggle.domain.model.entity.PostLike;
import com.munggle.domain.model.entity.PostLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeRespository extends JpaRepository<PostLike, PostLikeId> {

    Boolean existsByPostLikeIdAndIsDeletedFalse(PostLikeId postLikeId);

    List<PostLike> findByPostAndIsDeletedFalse(Post post);
}
