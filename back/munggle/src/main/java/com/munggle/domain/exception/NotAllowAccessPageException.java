package com.munggle.domain.exception;

public class NotAllowAccessPageException extends RuntimeException{
    public NotAllowAccessPageException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
