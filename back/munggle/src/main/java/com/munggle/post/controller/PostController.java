package com.munggle.post.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.post.dto.response.UserPostListDto;
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

    /**
     * 게시글 목록 (팔로우)
     *
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostListDto getPostList() {
        // 팔로잉: 최신순으로 정렬

        return null;
    }

    /**
     * 게시글 목록 (큐레이팅)
     *
     * @param principal
     * @return
     */
    @GetMapping("/curating")
    @ResponseStatus(HttpStatus.OK)
    public List<PostListDto> getCuratingPostList(@AuthenticationPrincipal User principal) {

        Long userId = principal.getId();
        List<PostListDto> curatingPosts = curatingService.getPostCuratingList(userId);

        return curatingPosts;
    }

    @GetMapping("/list/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserPostListDto> getAllUserPostList(@AuthenticationPrincipal User principal,
                                                    @PathVariable(value = "userId") Long userId) {
        return postService.getUserPost(userId, principal.getId());
    }

    /**
     * 게시글 상세 보기
     *
     * @param principal
     * @param postId
     * @return
     */
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDetailDto viewPost(@AuthenticationPrincipal User principal,
                                  @PathVariable(value = "postId") Long postId) {

        Long userId = principal.getId();
        return postService.getDetailPost(postId, userId);
    }

    /**
     * 게시글 등록
     *
     * @param principal
     * @param postCreateDto
     * @param files
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void savePost(@AuthenticationPrincipal User principal,
                         @RequestPart(value = "dto") @Valid PostCreateDto postCreateDto,
                         @RequestPart(value = "file", required = false) List<MultipartFile> files) {

        postCreateDto.setImages(files);
        postCreateDto.setUserId(principal.getId());
        postService.insertPost(postCreateDto);
    }

    /**
     * 게시글 수정
     *
     * @param postId
     * @param postUpdateDto
     * @param files
     */
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

    /**
     * 게시글 삭제
     *
     * @param postId
     */
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable(value = "postId") Long postId) {
        postService.deletePost(postId);
    }

    @PostMapping("/{postId}/like")
    @ResponseStatus(HttpStatus.OK)
    public void insertPostLike(@AuthenticationPrincipal User principal,
                            @PathVariable(value = "postId") Long postId) {

        Long userId = principal.getId();
        postService.insertPostLike(userId, postId);
    }

    @DeleteMapping("/{postId}/like")
    @ResponseStatus(HttpStatus.OK)
    public void deletePostLike(@AuthenticationPrincipal User principal,
                               @PathVariable(value = "postId") Long postId) {

        Long userId = principal.getId();
        postService.deletePostLike(userId, postId);
    }

}
