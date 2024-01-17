package com.munggle.post.controller;

import com.munggle.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.munggle.post.dto.PostListResponseDto;
import com.munggle.post.dto.PostListRequestDto;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostListResponseDto getPostList(@RequestBody PostListRequestDto postListRequestDto) {
        // 팔로잉 목록

        return null;
    }

    @GetMapping("/{post_no}")
    @ResponseStatus(HttpStatus.OK)
    public void viewPost() {}

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void create() {

    }
}
