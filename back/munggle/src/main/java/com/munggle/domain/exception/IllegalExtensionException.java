package com.munggle.domain.exception;

public class IllegalExtensionException extends RuntimeException{

    public IllegalExtensionException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
