package com.munggle.follow.service;

import com.munggle.user.dto.UserListDto;

import java.util.List;

public interface FollowService {

    List<UserListDto> getFollowerList(Long userId);

    void followUser(Long fromUserId, Long targetUserId);

    void unfollow(Long fromUserId, Long targetUserId);

    void deleteFollower(Long myId, Long followerId);

}
