package com.munggle.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;


@Getter
@Builder
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FollowId implements Serializable {

    @Column(name = "following_user_id")
    private Long followingUserId;

    @Column(name = "target_user_id")
    private Long targetUserId;
}
