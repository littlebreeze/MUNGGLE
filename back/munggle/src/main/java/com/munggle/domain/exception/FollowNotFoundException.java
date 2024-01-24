package com.munggle.domain.exception;

public class FollowNotFoundException extends RuntimeException{

    public FollowNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
