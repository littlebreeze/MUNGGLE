package com.munggle.domain.exception;

public class IllegalPasswordException extends RuntimeException{

    public IllegalPasswordException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
