package com.munggle.domain.exception;

public class MatchingNotOnException extends RuntimeException{
    public MatchingNotOnException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
