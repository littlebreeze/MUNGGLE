package com.munggle.domain.model.entity;

import jakarta.persistence.*;

@Entity
@IdClass(PostTagId.class)
@Table(name = "post_tags")
public class PostTag {

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

}
