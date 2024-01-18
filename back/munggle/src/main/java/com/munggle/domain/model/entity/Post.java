package com.munggle.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Size(max = 100)
    @NotNull
    @NotBlank
    @Column(name = "post_title")
    private String postTitle;

    @Size(max = 500)
    @Column(name = "post_content")
    private String postContent;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @Column(name = "like_cnt")
    @ColumnDefault("0")
    private Integer likeCnt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "is_private")
    private Boolean isPrivate;

    public void updatePost(String newTitle, String newContent, Boolean newIsPrivate){
        this.postTitle = newTitle;
        this.postContent = newContent;
        this.isPrivate = newIsPrivate;
    }
    public void markAsDeletd() {
        this.isDeleted = false;
    }
}
