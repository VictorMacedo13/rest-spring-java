package com.example.demo.services;

import com.example.demo.dto.security.NewAccountDTO;
import com.example.demo.dto.security.TokenDTO;
import com.example.demo.model.Permission;
import com.example.demo.model.User;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    PermissionRepository permissionRepository;


    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }

    @SuppressWarnings("rawtypes")
    public void CreateAccount(NewAccountDTO data) {


        Map<String, PasswordEncoder> encoders = new HashMap<>();
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
        encoders.put("pbkdf2", pbkdf2PasswordEncoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder);


        String password = passwordEncoder.encode(data.getPassword());
        String username = data.getUsername();
        String fullName = data.getFullName();

        List<Permission> permissions = new ArrayList<>();
        permissions.add(permissionRepository.findById(3L).get());

        var user = repository.findByUsername(username);
        TokenDTO tokenResponse;
        if (user != null) {
            throw new BadCredentialsException("Invalid username/password supplied ");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFullName(fullName);
        newUser.setPermissions(permissions);

        repository.save(newUser);


    }
}
