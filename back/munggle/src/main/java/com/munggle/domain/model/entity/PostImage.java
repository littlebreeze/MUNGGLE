package com.munggle.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "post_images")
public class PostImage {
    @Id
    @Column(name = "post_image_id")
    private Long id;

    private String imagePath;

    private Long postId;
}
