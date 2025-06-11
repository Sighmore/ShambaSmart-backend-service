package com.ShambaSmart.ShambaSmart.handler;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler implements ErrorController {

    /**
     * Handles IllegalArgumentException: this is used for handling invalid arguments in requests
     * @param ex the IllegalArgumentException: this exception is thrown when an illegal argument is passed
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(@NotNull IllegalArgumentException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
      *handle method argument not valid exceptions: this is used for validating request bodies
     * @param ex the MethodArgumentNotValidException: this exception is thrown when a method argument fails validation
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(@NotNull MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
            errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles all other exceptions: this is used for handling unexpected errors
     * @return a generic error response
     */
    @RequestMapping("/error")
    public ResponseEntity<Map<String, String>> handleError() {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "An unexpected error occurred.");
        return ResponseEntity.status(500).body(errorResponse);
    }


}

