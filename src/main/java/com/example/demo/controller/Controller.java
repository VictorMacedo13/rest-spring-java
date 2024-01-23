package com.example.demo.controller;

import com.example.demo.dto.ModelDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.services.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/controller")
@Tag(name = "Model CRUD", description = "CRUD Model API")
public class Controller {


    @Autowired
    ModelService service;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new Model", description = "Create a new Model", tags = {"Model CRUD"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = ModelDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "204", description = "No Content")
            }
    )
    public ModelDTO create(@RequestBody ModelDTO Model) {
        return service.create(Model);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find all Models", description = "Find all Models", tags = {"Model CRUD"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(array = @ArraySchema(
                                    schema = @Schema(implementation = ModelDTO.class)))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
            }
    )
    public List<ModelDTO> findAll() throws NotFoundException {

        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds a Model", description = "Finds a Model", tags = {"Model CRUD"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = ModelDTO.class))),
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            }
    )
    public ModelDTO findById(@PathVariable(value = "id") Long id) throws NotFoundException {

        return service.findById(id);
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a Model", description = "Update a Model", tags = {"Model CRUD"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = ModelDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            }
    )
    public ModelDTO update(@RequestBody ModelDTO model) throws NotFoundException {

        return service.update(model);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete a Model", description = "Delete a Model", tags = {"Model CRUD"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content",
                            content = @Content(schema = @Schema(implementation = ModelDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws NotFoundException {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

