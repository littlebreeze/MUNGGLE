package com.munggle.domain.exception;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
