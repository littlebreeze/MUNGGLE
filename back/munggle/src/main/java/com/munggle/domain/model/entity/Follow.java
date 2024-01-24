package com.munggle.domain.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "follows")
public class Follow {

    @EmbeddedId
    private FollowId followId;

    @ManyToOne
    @MapsId("followingUserId")
    @JoinColumn(name = "following_user_id")
    private User followUser;

    @ManyToOne
    @MapsId("targetUserId")
    @JoinColumn(name = "target_user_id")
    private User targetUser;

    @Column(name = "is_followed")
    private boolean isFollowed;
}
