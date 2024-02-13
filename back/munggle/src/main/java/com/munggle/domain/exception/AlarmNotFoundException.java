package com.munggle.domain.exception;

public class AlarmNotFoundException extends RuntimeException{
    public AlarmNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
