package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timeStemp;
    private String message;
    private String details;

}
