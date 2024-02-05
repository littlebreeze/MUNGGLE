package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity @Builder
@Getter
@Table(name = "post_likes")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class PostLike {

    @EmbeddedId
    private PostLikeId postLikeId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @MapsId("postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("userId")
    private User user;

    private boolean isDeleted;

    public void markAsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
