package com.munggle.post.repository;

import com.munggle.domain.model.entity.Post;
import com.munggle.post.dto.response.PostListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndIsDeletedFalse(Long id);

    @Query("select p from Post p where p.isDeleted = false order by p.likeCnt desc")
    List<Post> findAllOrderByLikeCntDesc();

    @Query("select distinct p from Post p " +
            "join p.postTagList pt " +
            "where pt.tag.tagNm in :tags and pt.isDeleted = false and p.isDeleted = false " +
            "order by p.likeCnt desc")
    List<Post> findByTagsInOrderByLikeCntDesc(@Param("tags") List<String> tags);

    @Query("select p from Post p where p.isDeleted = false and p.user.id = :userId order by p.createdAt desc")
    List<Post> findByUserIdAndIsDeletedFalse(@Param("userId") Long userId);
}
