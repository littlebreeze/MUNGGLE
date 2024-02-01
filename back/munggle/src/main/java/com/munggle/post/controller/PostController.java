package com.munggle.post.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.post.service.CuratingService;
import com.munggle.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.munggle.post.dto.request.PostCreateDto;
import com.munggle.post.dto.request.PostUpdateDto;
import com.munggle.post.dto.response.PostListDto;
import com.munggle.post.dto.response.PostDetailDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CuratingService curatingService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostListDto getPostList() {
        // 팔로잉: 최신순으로 정렬
        // 큐레이팅 순서로 정렬

        return null;
    }

    @GetMapping("/curating")
    @ResponseStatus(HttpStatus.OK)
    public List<PostListDto> getCuratingPostList(@AuthenticationPrincipal User principal) {

        Long userId = principal.getId();
        List<PostListDto> curatingPosts = curatingService.getPostCuratingList(userId);

        return curatingPosts;
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDetailDto viewPost(@AuthenticationPrincipal User principal,
                                  @PathVariable(value = "postId") Long postId) {

        Long userId = principal.getId();
        return postService.getDetailPost(postId, userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void savePost(@AuthenticationPrincipal User principal,
                         @RequestPart(value = "dto") @Valid PostCreateDto postCreateDto,
                         @RequestPart(value = "file", required = false) List<MultipartFile> files) {
//        log.info("제목: {}, 이미지: {}", postCreateDto.getPostTitle(), files.get(0));

        postCreateDto.setImages(files);
        postCreateDto.setUserId(principal.getId());
        postService.insertPost(postCreateDto);
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable(value = "postId") Long postId,
                           @RequestPart(value = "dto") @Valid PostUpdateDto postUpdateDto,
                           @RequestPart(value = "file", required = false) List<MultipartFile> files) {
        log.info("제목: {}, 이미지: {}", postUpdateDto.getPostTitle(), files.get(0));

        postUpdateDto.setImages(files);
        postUpdateDto.setPostId(postId);
        postService.updatePost(postUpdateDto);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable(value = "postId") Long postId) {
        postService.deletePost(postId);
    }

}
