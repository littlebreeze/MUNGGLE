package com.munggle.post.repository;

import com.munggle.domain.model.entity.UserRecentTagCache;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRecentTagCacheRepository extends CrudRepository<UserRecentTagCache, String> {
    List<UserRecentTagCache> findByUserId(Long userId);

    Optional<UserRecentTagCache> findByUserIdAndTagId(Long userId, Long tagId);
}
