package com.munggle.post.repository;

import com.munggle.domain.model.entity.Post;
import com.munggle.domain.model.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    @Modifying
    @Query("delete from PostImage pi where pi.post.id = :post_id")
    void deleteByPostId(@Param("post_id") Long post_id);
}
