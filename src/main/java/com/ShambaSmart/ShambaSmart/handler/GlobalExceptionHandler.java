package com.ShambaSmart.ShambaSmart.handler;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{

    //handle the null pointer exception and return a custom error message
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(@NotNull NullPointerException e) {
        // Log the exception (optional)
        System.out.println("Null pointer exception occurred: " + e.getMessage());

        //return a well formatted error message or view
        return "error/null-pointer";
    }

    //handle the illegal argument exception and return a custom error message
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(@NotNull IllegalArgumentException e) {
        // Log the exception (optional)
        System.out.println("Illegal argument exception occurred: " + e.getMessage());

        //return a well formatted error message or view
        return "error/illegal-argument";
    }

    //Method argument exception handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(@NotNull MethodArgumentNotValidException e) {
        // Log the exception (optional)
        System.out.println("Method argument not valid exception occurred: " + e.getMessage());

        // Return a well formatted error message or view
        return "error/method-argument-not-valid";
    }




   //generic exception handler for all exceptions
    @ExceptionHandler(Exception.class)
    public String handleException(@NotNull Exception e) {
        // Log the exception (optional)
        System.out.println("An error occurred: " + e.getMessage());

        // Return a generic error message or view
        return "error"; // This can be a view name or a response body
    }

}

