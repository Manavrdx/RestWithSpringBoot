package com.test.station.exceptions;

public class InternalServerException extends RuntimeException{

    public InternalServerException() {
        super("Internal Server Error..!");
    }

    public InternalServerException(final String message) {
        super(message);
    }

}
