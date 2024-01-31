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
    @MapsId("followFromId")
    @JoinColumn(name = "follow_from_id")
    private User followFrom;

    @ManyToOne
    @MapsId("followToId")
    @JoinColumn(name = "follow_to_id")
    private User followTo;

    @Column(name = "is_followed")
    private boolean isFollowed;

    public void follow() {
        isFollowed = true;
    }

    public void unfollow() {
        isFollowed = false;
    }
}
