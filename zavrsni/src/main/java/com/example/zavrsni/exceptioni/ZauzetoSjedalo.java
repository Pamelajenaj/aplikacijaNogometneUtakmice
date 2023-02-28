package com.example.zavrsni.exceptioni;

public class ZauzetoSjedalo extends RuntimeException{

    public ZauzetoSjedalo() {
    }

    public ZauzetoSjedalo(String message) {
        super(message);
    }

    public ZauzetoSjedalo(String message, Throwable cause) {
        super(message, cause);
    }

    public ZauzetoSjedalo(Throwable cause) {
        super(cause);
    }

    public ZauzetoSjedalo(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
