package com.munggle.comment.service;

import com.munggle.comment.dto.CommentCreateDto;
import com.munggle.comment.dto.CommentDetailDto;
import com.munggle.comment.dto.CommentUpdateDto;
import com.munggle.comment.mapper.CommentMapper;
import com.munggle.comment.repository.CommentRepository;
import com.munggle.domain.exception.CommentNotFoundException;
import com.munggle.domain.exception.ExceptionMessage;
import com.munggle.domain.exception.NotYourCommentException;
import com.munggle.domain.model.entity.Comment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

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
}
