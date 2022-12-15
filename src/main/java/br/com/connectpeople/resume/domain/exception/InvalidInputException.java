package br.com.connectpeople.resume.domain.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class InvalidInputException extends RuntimeException{

    private Map<String, String> errorDetails;
    private String error;
    private String message;

    public InvalidInputException(String error, String message) {
        this.error = error;
        this.message = message;

    }

    public InvalidInputException(Map<String, String> errors) {
        this.errorDetails = errors;
    }

}
