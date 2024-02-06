package com.example.demo.integrationtests.controller;

import com.example.demo.configs.TestConfigs;
import com.example.demo.dto.ModelDTO;
import com.example.demo.integrationtests.testcontainers.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ModelControllerTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static ModelDTO model;

    @BeforeAll
    public static void setup() {
//        specification = given().port(TestConfigs.SERVER_PORT);
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        model = new ModelDTO();
    }

    @Test
    @Order(1)
    public void shouldCreateAModel() throws JsonProcessingException {
        mockModel();
        specification = new RequestSpecBuilder().addHeader(TestConfigs.HEADER_PARAM_ORIGIN, "http://localhost:3000")
                .setBasePath("/controller").setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();


        var content = given().spec(specification).body(model).contentType("application/json")
                .when()
                .post()
                .then()
                .statusCode(200).extract()
                .body().asString();

        ModelDTO createdModel = objectMapper.readValue(content, ModelDTO.class);
        model = createdModel;
        assertNotNull(createdModel);
        assertNotNull(createdModel.getId());
        assertNotNull(createdModel.getName());
        assertNotNull(createdModel.getMessage());
        assertEquals(model.getName(), "Test");
        assertEquals(model.getMessage(), "Test message");
        assertTrue(createdModel.getId() > 0);
    }

    private void mockModel() {
        model.setName("Test");
        model.setMessage("Test message");
    }

}
