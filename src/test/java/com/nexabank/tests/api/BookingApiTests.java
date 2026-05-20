package com.nexabank.tests.api;

import com.nexabank.config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookingApiTests {

    @BeforeClass(alwaysRun = true)
    public void setup() {
        RestAssured.baseURI = ConfigReader.get("bookingApiBaseUrl");
    }

    @Test(groups = {"api", "smoke", "regression"})
    public void verifyBookingIdsAreReturned() {
        given()
        .when()
                .get("/booking")
        .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].bookingid", notNullValue());
    }

    @Test(groups = {"api", "regression"})
    public void verifyCreateBooking() {
        String payload = """
                {
                  "firstname": "Sushmit",
                  "lastname": "Mukherjee",
                  "totalprice": 1200,
                  "depositpaid": true,
                  "bookingdates": {
                    "checkin": "2026-06-01",
                    "checkout": "2026-06-10"
                  },
                  "additionalneeds": "Automation testing demo"
                }
                """;

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(payload)
        .when()
                .post("/booking")
        .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo("Sushmit"))
                .body("booking.lastname", equalTo("Mukherjee"))
                .body("booking.totalprice", equalTo(1200));
    }

    @Test(groups = {"api", "regression"})
    public void verifyHealthCheck() {
        given()
        .when()
                .get("/ping")
        .then()
                .statusCode(201);
    }
}
