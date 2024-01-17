package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Post extends BaseTimeEntity {

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
    @Column(name = "user_id")
    private Long user_id;

    @NotNull
    @Column(name = "like_cnt")
    @ColumnDefault("0")
    private Integer likeCnt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "is_private")
    private Boolean isPrivate;

}
