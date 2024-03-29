package com.example.demo.exception.handler;

import com.example.demo.exception.ExceptionResponse;
import com.example.demo.exception.InvalidJwtAuthenticationException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHendler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> genericException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> notFOundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(NotNullException.class)
    public final ResponseEntity<ExceptionResponse> notNullExeption(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> invalidJwtAuthenticationException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }
}
