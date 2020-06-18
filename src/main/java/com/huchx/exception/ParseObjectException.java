package com.huchx.exception;

public class ParseObjectException extends Exception {
    public ParseObjectException() {
    }

    public ParseObjectException(String message) {
        super(message);
    }

    public ParseObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseObjectException(Throwable cause) {
        super(cause);
    }

    public ParseObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
