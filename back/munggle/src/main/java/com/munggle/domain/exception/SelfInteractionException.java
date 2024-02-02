package com.munggle.domain.exception;

public class SelfInteractionException extends RuntimeException{
    public SelfInteractionException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
