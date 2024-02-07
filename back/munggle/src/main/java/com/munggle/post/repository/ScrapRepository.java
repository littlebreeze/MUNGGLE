package com.munggle.post.repository;

import com.munggle.domain.model.entity.Post;
import com.munggle.domain.model.entity.Scrap;
import com.munggle.domain.model.entity.ScrapId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, ScrapId> {

    Boolean existsByScrapIdAndIsDeletedFalse(ScrapId scrapId);

    @Query("select s.post from Scrap s where s.user.id = :userId and s.isDeleted = false")
    List<Post> findPostsByUserIdAndIsDeletedFalse(@Param("userId") Long userId);
}
