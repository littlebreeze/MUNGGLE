package com.munggle.follow.mapper;

import com.munggle.domain.model.entity.FollowId;

public class FollowMapper {

    public static FollowId toFollowId(Long fromId, Long toId) {
        return FollowId.builder()
                .followingUserId(fromId)
                .targetUserId(toId)
                .build();
    }
}
