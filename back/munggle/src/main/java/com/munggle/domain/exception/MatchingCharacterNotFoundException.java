package com.munggle.domain.exception;

public class MatchingCharacterNotFoundException extends RuntimeException{
    public MatchingCharacterNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
