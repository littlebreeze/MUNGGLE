package com.munggle.domain.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment_like_lists")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CommentLike {

    @EmbeddedId
    private CommentLikeId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("commentId")
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    public void toggleLike(){
        this.isDeleted = !this.isDeleted;
    }
}
