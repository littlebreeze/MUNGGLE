package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_no")
    private Long id;

    @Size(max = 100)
    @NotNull
    @NotBlank
    @Column(name = "post_title")
    private String postTitle;

    @Size(max = 500)
    @Column(name = "content")
    private String postContent;

    @NotNull
    @NotBlank
    @Column(name = "user_name")
    private Long userName;

    @NotNull
    @Column(name = "like_cnt")
    private Integer likeCnt;

    @NotNull
    @NotBlank
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "is_private")
    private Boolean isPrivate;

}
