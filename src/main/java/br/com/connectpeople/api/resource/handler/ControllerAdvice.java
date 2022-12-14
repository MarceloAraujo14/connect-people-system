package br.com.connectpeople.api.resource.handler;

import br.com.connectpeople.resume.domain.exception.InvalidInputException;
import br.com.connectpeople.resume.domain.exception.RegisterAlreadyExistsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidInputException.class)
    public Map<String, String> inputExceptionHandler(InvalidInputException ex){
        return ex.getErrorDetails();
    }

    @ExceptionHandler(RegisterAlreadyExistsException.class)
    public Map<String, String> inputExceptionHandler(RegisterAlreadyExistsException ex){
        return Map.of(ex.getError(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> genericExceptionHandler(Exception ex){
        return Map.of("Generic error:", ex.getMessage());
    }

}
