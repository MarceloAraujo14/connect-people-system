package br.com.connectpeople.api.resource;

import br.com.connectpeople.resume.domain.exception.InvalidInputException;
import br.com.connectpeople.resume.domain.exception.RegisterAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(BAD_REQUEST).body(errors);
    }

    @ExceptionHandler({InvalidInputException.class})
    public ResponseEntity<Map<String, String>> inputExceptionHandler(InvalidInputException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(ex.getErrorDetails());
    }

    @ExceptionHandler(RegisterAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> registerAlreadyExistsHandler(RegisterAlreadyExistsException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(Map.of(ex.getError(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> genericExceptionHandler(Exception ex) {
        return ResponseEntity.status(BAD_REQUEST).body(Map.of("Generic error:", ex.getMessage()));
    }

}
