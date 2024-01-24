package com.munggle.post.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.munggle.post.dto.PostCreateDto;
import com.munggle.post.dto.PostUpdateDto;
import com.munggle.post.dto.PostListResponseDto;
import com.munggle.post.dto.PostListRequestDto;
import com.munggle.post.dto.PostDetailResponseDto;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostListResponseDto getPostList(@RequestBody PostListRequestDto postListRequestDto) {
        // 팔로잉: 최신순으로 정렬
        // 큐레이팅 순서로 정렬

        return null;
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void viewPost() {}

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void savePost(@AuthenticationPrincipal User principal,
                         @RequestBody PostCreateDto postCreateDto) {

        postCreateDto.setUserId(principal.getId());
        postService.insertPost(postCreateDto);
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable Long postId,
                           @RequestBody @Valid PostUpdateDto postUpdateDto) {

        postUpdateDto.setPostId(postId);
        postService.updatePost(postUpdateDto);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDetailResponseDto getPost(@AuthenticationPrincipal User principal,
                                         @PathVariable Long postId) {

        Long userId = principal.getId();
        return postService.getDetailPost(postId, userId);
    }
}
