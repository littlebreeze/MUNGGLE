package com.munggle.comment.service;

import com.munggle.comment.dto.CommentCreateDto;
import com.munggle.comment.dto.CommentDetailDto;
import com.munggle.comment.dto.CommentUpdateDto;
import com.munggle.comment.mapper.CommentMapper;
import com.munggle.comment.repository.CommentLikeRepository;
import com.munggle.comment.repository.CommentRepository;
import com.munggle.domain.exception.CommentNotFoundException;
import com.munggle.domain.exception.ExceptionMessage;
import com.munggle.domain.exception.NotYourCommentException;
import com.munggle.domain.exception.UserNotFoundException;
import com.munggle.domain.model.entity.Comment;
import com.munggle.domain.model.entity.CommentLikeId;
import com.munggle.domain.model.entity.CommentLike;
import com.munggle.domain.model.entity.User;
import com.munggle.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;

    // 본인이 작성한 댓글인지 확인
    public void IsItYourComment(Long commentsUserId, Long loginUserId){
       if(loginUserId != commentsUserId)
           throw new NotYourCommentException(ExceptionMessage.NOT_YOUR_COMMENT);
    }

    @Override
    public void insertComment(CommentCreateDto commentCreateDto) {

        Comment comment = CommentMapper.toEntity(commentCreateDto);

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
    public List<CommentDetailDto> getCommentList(Long postId) {
        return commentRepository.findAllByPostIdAndIsDeletedFalse(postId)
                .orElseThrow(()->new CommentNotFoundException(ExceptionMessage.COMMENT_NOT_FOUND))
                .stream().map(comment -> CommentMapper.toDto(comment))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDetailDto getComment(Long commentId) {
        Comment comment = commentRepository.findByIdAndIsDeletedFalse(commentId)
                .orElseThrow(()->new CommentNotFoundException(ExceptionMessage.COMMENT_NOT_FOUND));

        return CommentMapper.toDto(comment);
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
