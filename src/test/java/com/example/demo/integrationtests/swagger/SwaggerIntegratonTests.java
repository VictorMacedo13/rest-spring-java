package com.example.demo.integrationtests.swagger;

import com.example.demo.configs.TestConfigs;
import com.example.demo.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegratonTests extends AbstractIntegrationTest {

    @Test
    public void shouldReturnSwaggerUI() {
        var content = given().basePath("/swagger-ui/index.html").port(TestConfigs.SERVER_PORT)
                .when()
                .get("/swagger-ui.html")
                .then()
                .statusCode(200).extract()
                .body().asString();
        assertTrue(content.contains("Swagger UI"));
    }

}
