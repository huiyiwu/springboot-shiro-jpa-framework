package com.huchx.exception;

public class ParameterMissException extends Exception {
    public ParameterMissException() {
    }

    public ParameterMissException(String message) {
        super(message);
    }
}
