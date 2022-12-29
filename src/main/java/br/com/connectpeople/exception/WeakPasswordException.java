package br.com.connectpeople.exception;

import lombok.Getter;

@Getter
public class WeakPasswordException extends RuntimeException {

    private final String error;
    private final String message;

    public WeakPasswordException(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
