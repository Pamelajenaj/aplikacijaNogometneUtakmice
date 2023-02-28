package com.example.zavrsni.exceptioni;

import java.security.spec.ECFieldF2m;

public class NepostojeceSjedalo extends Exception {

    public NepostojeceSjedalo() {
    }

    public NepostojeceSjedalo(String message) {
        super(message);
    }

    public NepostojeceSjedalo(String message, Throwable cause) {
        super(message, cause);
    }

    public NepostojeceSjedalo(Throwable cause) {
        super(cause);
    }

    public NepostojeceSjedalo(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
