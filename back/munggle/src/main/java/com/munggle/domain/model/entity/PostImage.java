package com.munggle.domain.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "post_images")
public class PostImage {
    @Id
    @Column(name = "post_image_id")
    private Long id;

    private String imageName;

    private String imageURL;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
