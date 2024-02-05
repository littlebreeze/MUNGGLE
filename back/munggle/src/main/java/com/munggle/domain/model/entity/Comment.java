package com.munggle.domain.model.entity;

import com.munggle.comment.dto.CommentUpdateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "comments")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Comment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @NotNull
    @NotBlank
    //@Size(max = )
    private String contents;

    @ColumnDefault("0")
    private Integer likeCnt;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    public void updateComment(CommentUpdateDto commentUpdateDto){
        this.contents = commentUpdateDto.getContents();
    }

    public void deleteComment(){
        this.isDeleted = true;
    }

    public void plusLike(){
        this.likeCnt++;
    }

    public void minusLike(){
        this.likeCnt--;
    }
}
