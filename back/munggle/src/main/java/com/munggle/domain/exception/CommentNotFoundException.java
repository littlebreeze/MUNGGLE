package com.munggle.domain.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
