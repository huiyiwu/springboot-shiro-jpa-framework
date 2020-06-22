package com.huchx.exception;

public class TokenExistException extends Exception {
    public TokenExistException() {
    }

    public TokenExistException(String message) {
        super(message);
    }

    public TokenExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenExistException(Throwable cause) {
        super(cause);
    }

    public TokenExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
