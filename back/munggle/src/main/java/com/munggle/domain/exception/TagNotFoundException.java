package com.munggle.domain.exception;

public class TagNotFoundException extends RuntimeException{
    public TagNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
