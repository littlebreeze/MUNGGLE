package com.munggle.post.service;

import com.munggle.post.dto.request.PostCreateDto;
import com.munggle.post.dto.response.PostDetailDto;
import com.munggle.post.dto.request.PostUpdateDto;


public interface PostService {
    void insertPost(PostCreateDto postCreateDto);

    void updatePost(PostUpdateDto postUpdateDto);

    void deletePost(Long postId);

    PostDetailDto getDetailPost(Long postId, Long userId);

    void postLike(Long userId, Long postId);

    void postScrap(Long userId, Long postId);
}
