package com.munggle.domain.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
