package fr.norsys.gestionReservations.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(" Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleIllegalArgumentException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>("Argument type not allowed: " + errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyUsedException.class)
    public ResponseEntity<String> handleResourceUsedException(AlreadyUsedException ex) {
        return new ResponseEntity<>("Error : " + ex.getMessage(), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(IllegalDatesFormatException.class)
    public ResponseEntity<String> handleIllegalDatesFormatException(IllegalDatesFormatException ex) {
        return new ResponseEntity<>("Error : " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }



}
