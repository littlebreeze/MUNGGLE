package com.munggle.domain.model.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter @Builder
@AllArgsConstructor
@RedisHash(value = "user_tag")
public class UserRecentTagCache {

    @Id
    private Long id;

    private Long userId;

    private Long tagId;
}

