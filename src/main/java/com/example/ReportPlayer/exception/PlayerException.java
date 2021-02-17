package com.example.ReportPlayer.exception;

public class PlayerException extends RuntimeException {

    public PlayerException() {
        super();
    }
    public PlayerException(String message, final Throwable cause) {
        super(message,cause);
    }
    public PlayerException(final String message) {
        super(message);
    }
    public PlayerException(final Throwable cause) {
        super(cause);
    }
}
