package com.example.demo.unitTests.mockito.services;

import com.example.demo.dto.ModelDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotNullException;
import com.example.demo.mapper.ObjectConverter;
import com.example.demo.model.Model;
import com.example.demo.repositories.ModelRepository;
import com.example.demo.services.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ModelServicesTest {

    ModelDTO modelDTO;

    Model model;

    @InjectMocks
    ModelService service;

    @Mock
    ModelRepository repository;

    @BeforeEach
    void setUp() {
        modelDTO = new ModelDTO();
        modelDTO.setId(1L);
        modelDTO.setName("teste name");
        modelDTO.setMessage("teste message");
        model = new Model();
        model.setId(1L);
        model.setName("teste name");
        model.setMessage("teste message");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        ModelDTO newModel = new ModelDTO();

        when(repository.save(ObjectConverter.convertObject(newModel, Model.class))).thenReturn(model);
        var result = service.create(newModel);
        assertEquals(1L, result.getId());
        assertEquals("teste name", result.getName());
        assertEquals("teste message", result.getMessage());

    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(new ArrayList<>(List.of(model)));
        var result = service.findAll();
        System.out.println(result.getFirst().toString());
//        var firstResult = result[0];
        assertNotNull(result);

    }

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(model));
        var result = service.findById(1L);
        assertEquals(1L, result.getId());
        assertEquals("teste name", result.getName());
        assertEquals("teste message", result.getMessage());

    }

    @Test
    void update() {
        ModelDTO newModel = new ModelDTO();
        newModel.setId(1L);
        newModel.setName("teste name");
        newModel.setMessage("teste message");

        when(repository.save(ObjectConverter.convertObject(newModel, Model.class))).thenReturn(model);
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(model));
        var result = service.update(newModel);
        assertEquals(1L, result.getId());
        assertEquals("teste name", result.getName());
        assertEquals("teste message", result.getMessage());
    }

    @Test
    void delete() {
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(model));
        service.delete(1L);
    }

    @Test
    void deleteNotFound() {
        when(repository.findById(1L)).thenReturn(java.util.Optional.empty());
        var exception = assertThrows(NotFoundException.class, () -> service.delete(1L));
        assertTrue(exception.getMessage().contains("no records found for this ID"));
    }

    @Test
    void findByIdNotFound() {
        when(repository.findById(1L)).thenReturn(java.util.Optional.empty());
        var exception = assertThrows(NotFoundException.class, () -> service.findById(1L));
        assertTrue(exception.getMessage().contains("no records found for this ID"));
    }

    @Test
    void createNull() {
        var exception = assertThrows(NotNullException.class, () -> service.create(null));
        assertTrue(exception.getMessage().contains("This field cannot be null"));
    }

    @Test
    void updateNull() {
        var exception = assertThrows(NotNullException.class, () -> service.update(null));
        assertTrue(exception.getMessage().contains("This field cannot be null"));
    }
}
