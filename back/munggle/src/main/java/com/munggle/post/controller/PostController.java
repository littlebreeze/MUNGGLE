package com.munggle.post.controller;

import com.munggle.domain.model.entity.User;
import com.munggle.post.dto.response.PagePostDto;
import com.munggle.post.service.PostListService;
import com.munggle.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final PostListService postListService;

    // === 팔로우 최신 게시글 === //
    @GetMapping("/following")
    @ResponseStatus(HttpStatus.OK)
    public PagePostDto getFollowingPostList(@AuthenticationPrincipal User principal,
                                            @PageableDefault(size = 30, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Long userId = principal.getId();
        PagePostDto pagePost = postListService.getFollowingPost(userId, pageable);

        return pagePost;
    }

    // === 큐레이팅 추천순 게시글 === //
    @GetMapping("/curating")
    @ResponseStatus(HttpStatus.OK)
    public List<PostListDto> getCuratingPostList(@AuthenticationPrincipal User principal) {

        Long userId = principal.getId();
        List<PostListDto> curatingPosts = postListService.getPostCuratingList(userId);

        return curatingPosts;
    }

    // === 게시글 상세보기 === //
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDetailDto viewPost(@AuthenticationPrincipal User principal,
                                  @PathVariable(value = "postId") Long postId) {

        Long userId = principal.getId();
        return postService.getDetailPost(postId, userId);
    }

    // === 게시글 등록 === //
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Long savePost(@AuthenticationPrincipal User principal,
                         @RequestBody @Valid PostCreateDto postCreateDto) {

        postCreateDto.setUserId(principal.getId());
        return postService.insertPost(postCreateDto);
    }

    // === 이미지 하나만 등록 === //
    @PostMapping("/{postId}/image")
    @ResponseStatus(HttpStatus.OK)
    public void savePostImage(@AuthenticationPrincipal User principal,
                              @PathVariable(value = "postId") Long postId,
                              @RequestPart(value = "file") MultipartFile file) {

        postService.savePostImage(file, postId, principal.getId());
    }

    // === 게시글 수정 === //
    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Long updatePost(@PathVariable(value = "postId") Long postId,
                           @RequestPart(value = "dto") @Valid PostUpdateDto postUpdateDto) {

        postUpdateDto.setPostId(postId);
        return postService.updatePost(postUpdateDto);
    }

    // === 게시글 삭제 === //
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable(value = "postId") Long postId) {
        postService.deletePost(postId);
    }

    // === 좋아요 등록 / 삭제 === //
    @PostMapping("/{postId}/like")
    @ResponseStatus(HttpStatus.OK)
    public void insertPostLike(@AuthenticationPrincipal User principal,
                            @PathVariable(value = "postId") Long postId) {

        Long userId = principal.getId();
        postService.postLike(userId, postId);
    }

    // === 스크랩 등록 / 삭제 === //
    @PostMapping("/{postId}/scrap")
    @ResponseStatus(HttpStatus.OK)
    public void deletePostLike(@AuthenticationPrincipal User principal,
                               @PathVariable(value = "postId") Long postId) {

        Long userId = principal.getId();
        postService.postScrap(userId, postId);
    }

}
