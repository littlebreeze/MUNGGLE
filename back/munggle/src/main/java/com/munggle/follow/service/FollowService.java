package com.munggle.follow.service;

import com.munggle.user.dto.UserListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowService {

    Page<UserListDto> getFollowerList(Long userId, Pageable pageable);

    Page<UserListDto> getFollowingList(Long userId, Pageable pageable);

    Integer getFollowerCount(Long userId);

    Integer getFollowingCount(Long userId);

    void followUser(Long fromUserId, Long targetUserId);

    void unfollow(Long fromUserId, Long targetUserId);

    void deleteFollower(Long myId, Long followerId);
}
