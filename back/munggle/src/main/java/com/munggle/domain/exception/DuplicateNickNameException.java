package com.munggle.domain.exception;

public class DuplicateNickNameException extends RuntimeException{

    public DuplicateNickNameException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
