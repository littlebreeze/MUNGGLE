package com.munggle.post.service;

import com.munggle.post.dto.PostCreateDto;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    void insertPost(PostCreateDto postCreateDto);
}
