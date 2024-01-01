package com.example.demo.controller;

import com.example.demo.model.Model;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping(value = "/controller", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> restGet(@RequestParam(value = "name", defaultValue = "World") String name) throws Exception{
        if(name.equals("error")){
            throw new Exception("teste");
        }
        return ResponseEntity.ok().body(new Model("A01", String.format("Hello %s!",name)));
    }
}

