package com.munggle.post.service;

import com.munggle.domain.model.entity.UserRecentTagCache;

import java.util.List;

public interface CuratingService {

    void saveViewPostTag(Long userId, Long postId);

    public void saveRecentTag(Long userId, Long tagId);

    List<UserRecentTagCache> getRecentTagsByUserId(Long userId);

    public void migrateRecentTags();
}
