package com.munggle.domain.exception;

public class LocationsNotFoundException extends RuntimeException{
    public LocationsNotFoundException(ExceptionMessage msg) {
        super(msg.getMessage());
    }
}
