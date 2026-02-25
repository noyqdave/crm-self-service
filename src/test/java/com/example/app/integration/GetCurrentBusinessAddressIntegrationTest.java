package com.example.app.integration;

import com.example.app.application.port.out.SaveBusinessAddressPort;
import com.example.app.adapters.outbound.persistence.repository.BusinessAddressJpaRepository;
import com.example.app.domain.BusinessAddress;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Slice integration tests for GET current business address (checklist steps 16–17).
 * Exercises the full stack: REST endpoint → use case → persistence.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DisplayName("Get current business address (integration)")
class GetCurrentBusinessAddressIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    SaveBusinessAddressPort saveBusinessAddressPort;

    @Autowired
    BusinessAddressJpaRepository jpaRepository;

    private static final String CUSTOMER_ID = "me";
    private static final BusinessAddress EXAMPLE_ADDRESS =
            new BusinessAddress("100 Main St", "Springfield", "Illinois", "62701", "United States");

    private static final String BUSINESS_ADDRESS_PATH = "/api/customers/me/business-address";

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "";
    }

    @Nested
    @DisplayName("16. Given a customer with a stored business address")
    class CustomerHasStoredAddress {

        @BeforeEach
        void setUpData() {
            saveBusinessAddressPort.save(CUSTOMER_ID, EXAMPLE_ADDRESS);
        }

        @Test
        @DisplayName("GET endpoint returns 200 and JSON with street, city, stateProvince, postalCode, country")
        void getReturns200WithJsonBody() {
            given()
                .accept(ContentType.JSON)
            .when()
                .get(BUSINESS_ADDRESS_PATH)
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("street", equalTo("100 Main St"))
                .body("city", equalTo("Springfield"))
                .body("stateProvince", equalTo("Illinois"))
                .body("postalCode", equalTo("62701"))
                .body("country", equalTo("United States"));
        }
    }

    @Nested
    @DisplayName("17. Given a customer with no stored address")
    class CustomerHasNoStoredAddress {

        @BeforeEach
        void ensureNoAddress() {
            jpaRepository.deleteById(CUSTOMER_ID);
        }

        @Test
        @DisplayName("GET endpoint returns 404")
        void getReturns404() {
            given()
                .accept(ContentType.JSON)
            .when()
                .get(BUSINESS_ADDRESS_PATH)
            .then()
                .statusCode(404);
        }
    }
}
