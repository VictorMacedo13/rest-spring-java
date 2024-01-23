package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotNullException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public NotNullException(String ex) {
        super(ex);
    }

    public NotNullException() {
        super("This field cannot be null.");
    }
}
