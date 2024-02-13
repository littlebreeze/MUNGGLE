package com.munggle.jwt;

import com.munggle.domain.exception.ExceptionMessage;

public class InvalidTokenException extends Exception{

    public InvalidTokenException(ExceptionMessage msg) {
        super(msg.getMessage());
    }

}
