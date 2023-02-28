package com.example.zavrsni.exceptioni;

public class PogresanUserPass extends RuntimeException{

    public PogresanUserPass() {
    }

    public PogresanUserPass(String message) {
        super(message);
    }

    public PogresanUserPass(String message, Throwable cause) {
        super(message, cause);
    }

    public PogresanUserPass(Throwable cause) {
        super(cause);
    }

    public PogresanUserPass(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
