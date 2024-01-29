package com.munggle.domain.exception;

public class BlockNotFoundException extends RuntimeException{

    public BlockNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
