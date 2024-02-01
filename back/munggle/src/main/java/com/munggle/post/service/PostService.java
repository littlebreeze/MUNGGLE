package com.munggle.post.service;

import com.munggle.post.dto.request.PostCreateDto;
import com.munggle.post.dto.response.PostDetailDto;
import com.munggle.post.dto.request.PostUpdateDto;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    void insertPost(PostCreateDto postCreateDto);

    void updatePost(PostUpdateDto postUpdateDto);

    void deletePost(Long postId);

    PostDetailDto getDetailPost(Long postId, Long userId);
}
