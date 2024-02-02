package com.munggle.domain.exception;

public class PasswordNotConfirmException extends RuntimeException{

    public PasswordNotConfirmException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
