package com.munggle.post.service;

import com.munggle.post.dto.request.PostCreateDto;
import com.munggle.post.dto.response.PostDetailDto;
import com.munggle.post.dto.request.PostUpdateDto;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    Long insertPost(PostCreateDto postCreateDto);

    Long updatePost(PostUpdateDto postUpdateDto);

    void savePostImage(MultipartFile image, Long postId, Long userId);

    void deletePost(Long postId);

    PostDetailDto getDetailPost(Long postId, Long userId);

    void postLike(Long userId, Long postId);

    void postScrap(Long userId, Long postId);

}
