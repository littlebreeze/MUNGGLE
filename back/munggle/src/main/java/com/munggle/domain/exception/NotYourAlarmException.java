package com.munggle.domain.exception;

public class NotYourAlarmException extends RuntimeException{
    public NotYourAlarmException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
