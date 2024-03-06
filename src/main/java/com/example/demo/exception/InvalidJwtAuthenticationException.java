package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException {
    public static final long serialVersionUID = 1L;

    public InvalidJwtAuthenticationException(String ex) {
        super(ex);
    }
    
}
