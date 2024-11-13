package com.training.dat.Wallet.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorResponse = new HashMap<>();

        FieldError error = ex.getBindingResult().getFieldErrors().get(0);
        String[] parts = error.getDefaultMessage().split(":", 2); // Splits on ":"
        
        if (parts.length == 2) {
            errorResponse.put("messageCode", parts[0].replace("{", ""));
            errorResponse.put("message", parts[1].replace("}", ""));
        } else {
            errorResponse.put("messageCode", "E100000"); // Default error code
            errorResponse.put("message", error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleDateTimeParseException(DateTimeParseException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("messageCode", "E100002"); // Custom error code for date format issues
        errorResponse.put("message", "Format of Date of Birth is not correct.");

        return ResponseEntity.badRequest().body(errorResponse);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("messageCode", "E230000");
        errorResponse.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        String[] parts = ex.getMessage().split(":", 2);

        if (parts.length == 2) {
            errorResponse.put("messageCode", parts[0]);
            errorResponse.put("message", parts[1]);
        } else {
            errorResponse.put("messageCode", "E100000");
            errorResponse.put("message", ex.getMessage());
        }

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
