package com.munggle.domain.exception;

public class WalkNotFoundException extends RuntimeException{
    public WalkNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
