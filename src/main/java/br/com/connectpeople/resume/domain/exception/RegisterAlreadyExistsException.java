package br.com.connectpeople.resume.domain.exception;

import lombok.Getter;

@Getter
public class RegisterAlreadyExistsException extends RuntimeException {

    private final String error;
    private final String message;

    public RegisterAlreadyExistsException(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
