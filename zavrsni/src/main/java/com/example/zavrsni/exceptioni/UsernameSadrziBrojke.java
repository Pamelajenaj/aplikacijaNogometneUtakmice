package com.example.zavrsni.exceptioni;

public class UsernameSadrziBrojke extends Exception{

    public UsernameSadrziBrojke() {
    }

    public UsernameSadrziBrojke(String message) {
        super(message);
    }

    public UsernameSadrziBrojke(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameSadrziBrojke(Throwable cause) {
        super(cause);
    }

    public UsernameSadrziBrojke(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
