package com.munggle.follow.mapper;

import com.munggle.domain.model.entity.FollowId;

public class FollowMapper {

    public static FollowId toFollowId(Long fromId, Long toId) {
        return FollowId.builder()
                .followFromId(fromId)
                .followToId(toId)
                .build();
    }
}
