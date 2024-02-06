package com.munggle.comment.repository;

import com.munggle.domain.model.entity.CommentLikeId;
import com.munggle.domain.model.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {
}
