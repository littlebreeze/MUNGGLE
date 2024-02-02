package com.munggle.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "user_tag", timeToLive = 100)
public class UserRecentTagCache implements Serializable {

    @Id
    @Indexed
    private String id;

    @Indexed
    private Long userId;

    @Indexed
    private Long tagId;
}

