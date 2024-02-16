package com.munggle.domain.exception;

public class NotYourCommentException extends RuntimeException{
    public NotYourCommentException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
