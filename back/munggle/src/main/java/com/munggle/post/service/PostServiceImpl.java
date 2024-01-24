package com.munggle.post.service;

import com.munggle.domain.exception.UserNotFoundException;
import com.munggle.domain.model.entity.Post;
import com.munggle.domain.model.entity.PostImage;
import com.munggle.image.dto.FileInfoDto;
import com.munggle.image.service.FileS3UploadService;
import com.munggle.post.dto.PostCreateDto;
import com.munggle.post.dto.PostDetailResponseDto;
import com.munggle.post.dto.PostUpdateDto;
import com.munggle.post.mapper.PostMapper;
import com.munggle.post.repository.PostImageRepository;
import com.munggle.post.repository.PostRepository;
import com.munggle.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

import static com.munggle.domain.exception.ExceptionMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final FileS3UploadService fileS3UploadService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostImageRepository postImageRepository;

    /**
     * 게시글 생성 메소드
     *
     * 이미지 생성 구현 예정
     * 추후 해시태그 생성 구현 예정
     * @param postCreateDto
     */
    @Override
    @Transactional
    public void insertPost(PostCreateDto postCreateDto) {

        Post newPost = PostMapper.toEntity(postCreateDto);
        Long userId = postCreateDto.getUserId();
        newPost.addUserToPost(userRepository.findByIdAndIsEnabledTrue(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND)));

        List<MultipartFile> files = postCreateDto.getImages();
        String uploadPath = "/post";
        List<FileInfoDto> fileInfoDtos = fileS3UploadService.uploadFlieList(uploadPath, files);
        for (FileInfoDto fileInfo : fileInfoDtos) {
            PostImage newImage = PostMapper.toPostImageEntity(fileInfo, newPost);
            postImageRepository.save(newImage);
        }

        postRepository.save(newPost);
    }

    /**
     * 게시글 수정 메소드
     *
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
