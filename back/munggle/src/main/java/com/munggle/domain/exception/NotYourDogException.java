package com.munggle.domain.exception;

public class NotYourDogException extends RuntimeException{
    public NotYourDogException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
