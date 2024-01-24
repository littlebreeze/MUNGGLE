package com.munggle.follow.service;

public interface FollowService {
    void followUser(Long fromUserId, Long targetUserId);
}
