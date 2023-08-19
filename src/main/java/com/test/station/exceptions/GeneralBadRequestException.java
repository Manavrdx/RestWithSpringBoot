package com.test.station.exceptions;

public class GeneralBadRequestException extends RuntimeException{
    public GeneralBadRequestException(final String message) {
        super(message);
    }
}
