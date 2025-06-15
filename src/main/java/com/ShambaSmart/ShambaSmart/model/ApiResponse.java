package com.ShambaSmart.ShambaSmart.model;

import lombok.Getter;
import lombok.Setter;


/**
 * A generic API response class that encapsulates the response message, data, and success status.
 *
 * @param <T> the type of data contained in the response
 */
@Getter
@Setter
public class ApiResponse <T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponse(String message, T data, boolean success) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
