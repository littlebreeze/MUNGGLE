package com.munggle.domain.exception;

public class NotYourWalkException extends RuntimeException{
    public NotYourWalkException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
