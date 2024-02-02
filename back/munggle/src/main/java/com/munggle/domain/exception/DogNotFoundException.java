package com.munggle.domain.exception;

public class DogNotFoundException extends RuntimeException{
    public DogNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
