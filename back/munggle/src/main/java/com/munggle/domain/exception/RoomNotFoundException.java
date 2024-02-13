package com.munggle.domain.exception;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
