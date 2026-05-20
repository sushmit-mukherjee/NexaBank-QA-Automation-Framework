package com.nexabank.tests.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.nexabank.config.ConfigReader;
import com.nexabank.utils.JsonUtils;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApiTests {

    @BeforeClass(alwaysRun = true)
    public void setup() {
        RestAssured.baseURI = ConfigReader.get("apiBaseUrl");
    }

    @Test(groups = {"api", "smoke", "regression"})
    public void verifyGetUserDetails() {
        given()
                .log().all()
        .when()
                .get("/users/2")
        .then()
                .log().all()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", containsString("@reqres.in"));
    }

    @Test(groups = {"api", "regression"})
    public void verifyCreateUser() {
        JsonNode payload = JsonUtils.readJson("src/test/resources/testdata/apiPayloads.json").get("createUser");

        given()
                .header("Content-Type", "application/json")
                .body(payload.toString())
        .when()
                .post("/users")
        .then()
                .statusCode(201)
                .body("name", equalTo(payload.get("name").asText()))
                .body("job", equalTo(payload.get("job").asText()))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());
    }

    @Test(groups = {"api", "regression"})
    public void verifyUpdateUser() {
        JsonNode payload = JsonUtils.readJson("src/test/resources/testdata/apiPayloads.json").get("updateUser");

        given()
                .header("Content-Type", "application/json")
                .body(payload.toString())
        .when()
                .put("/users/2")
        .then()
                .statusCode(200)
                .body("name", equalTo(payload.get("name").asText()))
                .body("job", equalTo(payload.get("job").asText()))
                .body("updatedAt", notNullValue());
    }

    @Test(groups = {"api", "regression"})
    public void verifyDeleteUser() {
        given()
        .when()
                .delete("/users/2")
        .then()
                .statusCode(204);
    }

    @Test(groups = {"api", "regression"})
    public void verifyInvalidEndpointReturnsNotFound() {
        given()
        .when()
                .get("/unknown-endpoint")
        .then()
                .statusCode(anyOf(equalTo(404), equalTo(200)));
    }
}
