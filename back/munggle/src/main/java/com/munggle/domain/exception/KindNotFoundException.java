package com.munggle.domain.exception;

public class KindNotFoundException extends RuntimeException{
    public KindNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
