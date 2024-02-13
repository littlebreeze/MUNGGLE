package com.munggle.domain.exception;

public class NotYourPostException extends RuntimeException{
    public NotYourPostException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
