package com.munggle.post.repository;

import com.munggle.domain.model.entity.Post;
import com.munggle.domain.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndIsDeletedFalse(Long id);
    
    @Query("select p from Post p where p.isDeleted = false and p.user.id = :userId order by p.createdAt desc")
    List<Post> findByUserIdAndIsDeletedFalse(@Param("userId") Long userId);

    @Query("select p from Post p where p.isDeleted = false and p.isPrivate = false and p.user.id = :userId order by p.createdAt desc")
    List<Post> findByUserIdAndIsDeletedFalseAndIsPrivateFalse(@Param("userId") Long userId);

    @Query("select p from Post p where p.isDeleted = false and p.user.id <> :userId order by p.likeCnt DESC")
    List<Post> findAllAndNotMineOrderByLikeCntDesc(@Param("userId") Long userId);

    @Query("select distinct p from Post p " +
            "join p.postTagList pt " +
            "where pt.tag.tagNm in :tags and pt.isDeleted = false " +
            "and p.isDeleted = false and p.user.id <> :userId " +
            "order by p.likeCnt desc")
    List<Post> findByTagsAndNotMineOrderByLikeCntDesc(@Param("tags") List<String> tags, @Param("userId") Long userId);

    @Query("select p from Post p where p.postTitle like concat('%', :word, '%') " +
            "and p.isDeleted = false and p.isPrivate = false " +
            "order by p.updatedAt desc")
    List<Post> searchByPostTitle(@Param("word") String word);

    @Query("select distinct p from Post p join fetch p.postTagList pt " +
            "where pt.tag.tagNm = :word and pt.isDeleted = false " +
            "and p.isDeleted = false and p.isPrivate = false " +
            "order by p.updatedAt desc")
    List<Post> searchByTagNm(@Param("word") String word);


    @Query("select p from Post p where p.postTitle like concat('%', :word, '%') " +
            "and p.isDeleted = false and p.isPrivate = false")
    Page<Post> searchByPostTitlePage(@Param("word") String word, Pageable pageable);


    @Query("select distinct p from Post p join fetch p.postTagList pt " +
            "where pt.tag.tagNm = :word and pt.isDeleted = false " +
            "and p.isDeleted = false and p.isPrivate = false")
    Page<Post> searchByTagNmPage(@Param("word") String word, Pageable pageable);

    @Query("select distinct p from Post p " +
            "where p.isDeleted = false and p.isPrivate = false " +
            "and p.user in :users " +
            "order by p.createdAt desc")
    Page<Post> findLatestPostsByUsers(
            @Param("users") List<User> users,
            Pageable pageable
    );

}
