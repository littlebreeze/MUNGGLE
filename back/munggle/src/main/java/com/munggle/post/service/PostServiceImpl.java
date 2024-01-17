package com.munggle.post.service;

import com.munggle.domain.model.entity.Post;
import com.munggle.post.dto.PostCreateDto;
import com.munggle.post.dto.PostUpdateDto;
import com.munggle.post.mapper.PostMapper;
import com.munggle.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public void insertPost(PostCreateDto postCreateDto) {
        Post newPost = PostMapper.toEntity(postCreateDto);
        postRepository.save(newPost);
    }

    @Override
    public void updatePost(PostUpdateDto postUpdateDto) {

    }
}
