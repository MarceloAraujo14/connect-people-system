package br.com.connectpeople.api.resource.handler;

import br.com.connectpeople.resume.domain.exception.InvalidInputException;
import br.com.connectpeople.resume.domain.exception.RegisterAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Map<String, String>> inputExceptionHandler(InvalidInputException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(ex.getErrorDetails());
    }

    @ExceptionHandler(RegisterAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> registerAlreadyExistsHandler(RegisterAlreadyExistsException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(Map.of(ex.getError(), ex.getMessage()));
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, String>> genericExceptionHandler(Exception ex) {
//        return ResponseEntity.status(BAD_REQUEST).body(Map.of("Generic error:", ex.getMessage()));
//    }

}
