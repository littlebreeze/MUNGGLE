package com.munggle.post.repository;

import com.munggle.domain.model.entity.Post;
import com.munggle.domain.model.entity.Scrap;
import com.munggle.domain.model.entity.ScrapId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, ScrapId> {

    Boolean existsByScrapIdAndIsDeletedFalse(ScrapId scrapId);

    List<Post> findByUserIdAndIsDeletedFalse(Long findUserId);
}
