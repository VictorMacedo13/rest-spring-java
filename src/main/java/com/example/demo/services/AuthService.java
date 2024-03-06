package com.example.demo.services;

import com.example.demo.dto.security.AccountCredentialsDTO;
import com.example.demo.dto.security.TokenDTO;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;


    @SuppressWarnings("rawtypes")
    public ResponseEntity login(AccountCredentialsDTO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);
            TokenDTO tokenResponse;
            if (user != null) {
                tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username not found!");
            }
            return ResponseEntity.ok(jwtTokenProvider.createAccessToken(data.getUsername(), repository.findByUsername(data.getUsername()).getRoles()));
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }


    @SuppressWarnings("rawtypes")
    public ResponseEntity refreShToken(String username, String refreshToken) {
        var user = repository.findByUsername(username);
        TokenDTO tokenResponse;

        if (user != null) {
            tokenResponse = jwtTokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username not found!");
        }
        return ResponseEntity.ok(tokenResponse);


    }
}
