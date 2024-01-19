package com.munggle.domain.model.entity;

public class Comment extends BaseTimeEntity{

    private Integer commentId;
    private Integer userId;
    private Integer articleId;
    private String contents;
    private Integer likeCnt;
    private boolean isDeleted;
}
