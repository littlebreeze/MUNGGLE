package com.munggle.domain.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_images")
public class UserImage {
    @Id
    @Column(name = "user_image_id")
    private Long id;

    private String imageName;

    private String imageURL;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String imageType;
}
