package com.munggle.domain.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
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

    @MapsId("postId")
    private Long postId;

    @MapsId("userId")
    private Long userId;

    private boolean isDeleted;
}
