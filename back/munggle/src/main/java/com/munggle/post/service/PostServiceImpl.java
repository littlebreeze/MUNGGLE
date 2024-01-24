package com.munggle.post.service;

import com.munggle.domain.model.entity.Post;
import com.munggle.post.dto.PostCreateDto;
import com.munggle.post.dto.PostDetailResponseDto;
import com.munggle.post.dto.PostUpdateDto;
import com.munggle.post.mapper.PostMapper;
import com.munggle.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    /**
     * 게시글 생성 메소드
     * 추후 해시태그 생성 구현 예정
     * @param postCreateDto
     */
    @Override
    @Transactional
    public void insertPost(PostCreateDto postCreateDto) {
        String title = postCreateDto.getPostTitle();
        
        Post newPost = PostMapper.toEntity(postCreateDto);
        postRepository.save(newPost);
    }

    /**
     * 게시글 수정 메소드
     * 수정 가능 필드: title / content / isPrivate
     * 추후 해시태그 수정 구현 예정
     * 이미지 수정 구현 예정
     * @param postUpdateDto
     */
    @Override
    @Transactional
    public void updatePost(PostUpdateDto postUpdateDto) {

        String newTitle = postUpdateDto.getPostTitle();
        String newContent = postUpdateDto.getPostContent();
        Boolean newIsPrivate = postUpdateDto.getIsPrivate();

        Post updatePost = postRepository.findById(postUpdateDto.getPostId())
                .orElseThrow();

        updatePost.updatePost(newTitle, newContent, newIsPrivate);
    }

    /**
     * 게시글 삭제 메소드
     * 추후 해시태그 삭제 / 파일 삭제 구현 예정
     * @param postId
     */
    @Override
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findByIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new NoSuchElementException());
        post.markAsDeletd();
    }

    /**
     * 게시글 상세보기 메소드
     *
     * @param postId
     * @param userId
     * @return
     */
    @Override
    public PostDetailResponseDto getDetailPost(Long postId, Long userId) {
        Post post = postRepository.findByIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new NoSuchElementException());
        String nickname = "nickname"; // 닉네임 추후 수정
        Boolean isMine = true;
        PostDetailResponseDto detailPost = PostMapper.toPostDetailResponseDto(post, nickname, isMine);

        return detailPost;
    }
}
