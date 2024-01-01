package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EspecificException extends RuntimeException{

    public EspecificException(String ex){
        super(ex);
    }
    private static final long serialVersionUID = 1L;
}
