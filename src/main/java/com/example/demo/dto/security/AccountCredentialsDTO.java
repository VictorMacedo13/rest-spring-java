package com.example.demo.dto.security;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AccountCredentialsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

}
