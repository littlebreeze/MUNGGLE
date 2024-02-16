package com.munggle.domain.exception;

public class IllegalSearchTypeException extends RuntimeException{

    public IllegalSearchTypeException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
