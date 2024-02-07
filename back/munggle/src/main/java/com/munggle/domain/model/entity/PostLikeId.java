package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Getter
@Builder
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeId implements Serializable {

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_id")
    private Long userId;

}
