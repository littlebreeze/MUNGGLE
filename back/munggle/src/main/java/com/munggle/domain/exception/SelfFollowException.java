package com.munggle.domain.exception;

public class SelfFollowException extends RuntimeException{
    public SelfFollowException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
