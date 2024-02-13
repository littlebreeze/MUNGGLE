package com.munggle.comment.service;

import com.munggle.alarm.service.AlarmService;
import com.munggle.comment.dto.CommentCreateDto;
import com.munggle.comment.dto.CommentDetailDto;
import com.munggle.comment.dto.CommentUpdateDto;
import com.munggle.comment.dto.PageCommentDto;
import com.munggle.comment.mapper.CommentMapper;
import com.munggle.comment.repository.CommentLikeRepository;
import com.munggle.comment.repository.CommentRepository;
import com.munggle.domain.exception.*;
import com.munggle.domain.model.entity.*;
import com.munggle.post.repository.PostRepository;
import com.munggle.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.munggle.domain.exception.ExceptionMessage.POST_NOT_FOUND;
import static com.munggle.domain.exception.ExceptionMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;
    private final AlarmService alarmService;
    private final PostRepository postRepository;

    // 본인이 작성한 댓글인지 확인
    public void IsItYourComment(Long commentsUserId, Long loginUserId){
       if(loginUserId != commentsUserId)
           throw new NotYourCommentException(ExceptionMessage.NOT_YOUR_COMMENT);
    }

    @Override
    @Transactional
    public void insertComment(CommentCreateDto commentCreateDto) {
        Post post = postRepository.findByIdAndIsDeletedFalse(commentCreateDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));
        User user = userRepository.findByIdAndIsEnabledTrue(commentCreateDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        Comment comment = CommentMapper.toEntity(commentCreateDto.getContents(), post, user);

        // 댓글 알림 생성
        if (!comment.getUser().equals(comment.getPost().getUser())) {
            alarmService.insertAlarm("COMMENT", user, post.getUser(), post.getId());
        }
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void updateComment(Long userId, CommentUpdateDto commentUpdateDto) {

        Comment comment = commentRepository.findByIdAndIsDeletedFalse(commentUpdateDto.getCommentId())
                .orElseThrow(()->new CommentNotFoundException(ExceptionMessage.COMMENT_NOT_FOUND));

        // 본인 댓글인지 확인
        IsItYourComment(comment.getUser().getId(), userId);

        comment.updateComment(commentUpdateDto);

    }

    @Override
    public PageCommentDto getCommentList(User principal, Long postId, Pageable pageable) {
        Page<Comment> pageComment = commentRepository.findAllByPostIdAndIsDeletedFalse(postId, pageable);

        List<CommentDetailDto> comments = commentRepository.findAllByPostIdAndIsDeletedFalse(postId, pageable)
                .stream().map(comment -> {
                    Boolean haveLiked = false;

                    if(principal != null)
                        haveLiked = commentLikeRepository.existsByIdAndIsDeletedFalse(CommentLikeId
                                .builder().commentId(comment.getId()).userId(principal.getId()).build());

                    return CommentMapper.toDto(comment, haveLiked);
                })
                .collect(Collectors.toList());

        Integer totalCnt = commentRepository.countByPostIdAndIsDeletedFalse(postId);

        return PageCommentDto.builder()
                .last(pageComment.isLast())
                .comments(comments)
                .totalCnt(totalCnt)
                .build();
    }

    @Override
    public CommentDetailDto getComment(User principal, Long commentId) {
        Comment comment = commentRepository.findByIdAndIsDeletedFalse(commentId)
                .orElseThrow(()->new CommentNotFoundException(ExceptionMessage.COMMENT_NOT_FOUND));

        Boolean haveLiked = false;

        if(principal != null)
            haveLiked = commentLikeRepository.existsByIdAndIsDeletedFalse(CommentLikeId
                .builder().commentId(commentId).userId(principal.getId()).build());

        return CommentMapper.toDto(comment, haveLiked);
    }

    @Override
    @Transactional
    public void deleteComment(Long userId, Long commentId) {

        Comment comment = commentRepository.findByIdAndIsDeletedFalse(commentId)
                .orElseThrow(()->new CommentNotFoundException(ExceptionMessage.COMMENT_NOT_FOUND));

        // 본인 댓글인지 확인
        IsItYourComment(comment.getUser().getId(), userId);

        comment.deleteComment();
    }

    @Override
    @Transactional
    public void toggleComment(Long userId, Long commentId) {

        //Like를 구현하면 댓글 상세에 내가 좋아요한 여부를 같이 보내줘야 한다.

        User user = userRepository.findByIdAndIsEnabledTrue(userId)
                .orElseThrow(()-> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND));
        Comment comment = commentRepository.findByIdAndIsDeletedFalse(commentId)
                .orElseThrow(()->new CommentNotFoundException(ExceptionMessage.COMMENT_NOT_FOUND));

        // 복합키 생성
        CommentLikeId commentLikeId = CommentLikeId.builder().userId(userId).commentId(commentId).build();

        // 이미 있는지 확인하고 없으면 생성
        Optional<CommentLike> commentLike = commentLikeRepository.findById(commentLikeId);

        // 비어있으면 새로 생성
        if(commentLike.isEmpty()){
            CommentLike newCommentLike = CommentLike.builder().id(commentLikeId).comment(comment).user(user).isDeleted(false).build();
            commentLikeRepository.save(newCommentLike);
            comment.plusLike();

        // 있으면 좋아요 여부 토글
        }else{
            // 삭제되어있었으면
            if(comment.getIsDeleted())
                comment.plusLike();
            else
                comment.minusLike();
            commentLike.get().toggleLike();
        }

    }
}
