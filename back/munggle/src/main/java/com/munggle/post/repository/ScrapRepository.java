package com.munggle.post.repository;

import com.munggle.domain.model.entity.Scrap;
import com.munggle.domain.model.entity.ScrapId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, ScrapId> {
}
