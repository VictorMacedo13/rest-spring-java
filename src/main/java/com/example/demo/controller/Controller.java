package com.example.demo.controller;

import com.example.demo.dto.ModelDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/controller")
public class Controller {


    @Autowired
    ModelService service;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelDTO create(@RequestBody ModelDTO Model) throws Exception {

        return service.create(Model);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ModelDTO> findAll() throws NotFoundException {

        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelDTO findById(@PathVariable(value = "id") Long id) throws NotFoundException {

        return service.findById(id);
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelDTO update(@RequestBody ModelDTO model) throws NotFoundException {

        return service.update(model);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws NotFoundException {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

