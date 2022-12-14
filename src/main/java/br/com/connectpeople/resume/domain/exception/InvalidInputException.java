package br.com.connectpeople.resume.domain.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class InvalidInputException extends RuntimeException{

    private String error;
    private String message;

    private final Map<String, String> errorDetails;

    public InvalidInputException(String error, String message) {
        this.error = error;
        this.message = message;
        this.errorDetails = Map.of(error, message);
    }

    public InvalidInputException(Map<String, String> errors) {
        this.errorDetails = errors;
    }
}
