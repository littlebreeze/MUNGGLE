package com.munggle.domain.exception;

public class IllegalAlarmTypeException extends RuntimeException{

    public IllegalAlarmTypeException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
