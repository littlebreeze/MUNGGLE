package com.munggle.domain.exception;

public class OpenAPIErrorException extends RuntimeException {

    public OpenAPIErrorException(ExceptionMessage msg, String error) {
        super(msg.getMessage()+error);
    }
}
