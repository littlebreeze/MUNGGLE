package com.munggle.domain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_images")
public class UserImage {
    @Id
    @Column(name = "user_image_id")
    private Long id;

    private String imagePath;

    private Long userId;

    private String imageType;
}
