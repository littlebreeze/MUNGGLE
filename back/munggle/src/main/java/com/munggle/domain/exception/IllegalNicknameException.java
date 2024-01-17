package com.munggle.domain.exception;

public class IllegalNicknameException extends RuntimeException{
    public IllegalNicknameException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
