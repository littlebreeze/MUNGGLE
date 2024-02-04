package com.munggle.post.repository;

import com.munggle.domain.model.entity.Post;
import com.munggle.search.dto.SearchPostListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndIsDeletedFalse(Long id);

    @Query("select p from Post p where p.postTitle like concat('%', :word, '%') " +
            "and p.isDeleted = false and p.isPrivate = false " +
            "order by p.updatedAt desc")
    List<Post> searchByPostTitle(@Param("word") String word);

    @Query("select distinct p from Post p join fetch p.postTagList pt " +
            "where pt.tag.tagNm = :word and pt.isDeleted = false " +
            "and p.isDeleted = false and p.isPrivate = false " +
            "order by p.updatedAt desc")
    List<Post> searchByTagNm(@Param("word") String word);

}
