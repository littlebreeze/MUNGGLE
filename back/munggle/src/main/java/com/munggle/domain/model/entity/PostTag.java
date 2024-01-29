package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity @Builder
@IdClass(PostTagId.class)
@Table(name = "post_tags")
@NoArgsConstructor
@AllArgsConstructor
public class PostTag {

    @EmbeddedId
    private PostTagId postTagId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @MapsId("postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    @MapsId("tagId")
    private Tag tag;

    @NotNull
    private Boolean isDeleted;

    public void markAsDeletd() {
        this.isDeleted = true;
    }


    public void markAsDeletdFalse() {
        this.isDeleted = false;
    }
}
