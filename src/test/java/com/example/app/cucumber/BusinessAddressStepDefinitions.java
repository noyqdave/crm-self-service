package com.example.app.cucumber;

import com.example.app.application.port.out.SaveBusinessAddressPort;
import com.example.app.adapters.outbound.persistence.repository.BusinessAddressJpaRepository;
import com.example.app.domain.BusinessAddress;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions for the business address feature.
 */
public class BusinessAddressStepDefinitions {

    private static final String CUSTOMER_ID = "me";

    @Autowired
    SaveBusinessAddressPort saveBusinessAddressPort;

    @Autowired
    BusinessAddressJpaRepository jpaRepository;

    @Autowired
    Environment environment;

    private Response lastResponse;

    @Given("the customer has business address:")
    public void theCustomerHasBusinessAddress(DataTable dataTable) {
        Map<String, String> row = dataTable.asMaps().getFirst();
        var address = new BusinessAddress(
                row.get("street"),
                row.get("city"),
                row.get("stateProvince"),
                row.get("postalCode"),
                row.get("country")
        );
        saveBusinessAddressPort.save(CUSTOMER_ID, address);
    }

    @When("the customer requests their current business address")
    public void theCustomerRequestsTheirCurrentBusinessAddress() {
        int port = Integer.parseInt(environment.getProperty("local.server.port", "8080"));
        lastResponse = RestAssured.given()
                .port(port)
                .accept("application/json")
                .when()
                .get("/api/customers/me/business-address");
    }

    @Then("the customer sees their business address:")
    public void theCustomerSeesTheirBusinessAddress(DataTable dataTable) {
        Map<String, String> expected = dataTable.asMaps().getFirst();
        assertThat(lastResponse.getStatusCode()).isEqualTo(200);
        assertThat(lastResponse.jsonPath().getString("street")).isEqualTo(expected.get("street"));
        assertThat(lastResponse.jsonPath().getString("city")).isEqualTo(expected.get("city"));
        assertThat(lastResponse.jsonPath().getString("stateProvince")).isEqualTo(expected.get("stateProvince"));
        assertThat(lastResponse.jsonPath().getString("postalCode")).isEqualTo(expected.get("postalCode"));
        assertThat(lastResponse.jsonPath().getString("country")).isEqualTo(expected.get("country"));
    }

    @Given("the customer has no business address")
    public void theCustomerHasNoBusinessAddress() {
        jpaRepository.deleteById(CUSTOMER_ID);
    }

    @Then("the response status is {int}")
    public void theResponseStatusIs(int expectedStatus) {
        assertThat(lastResponse.getStatusCode()).isEqualTo(expectedStatus);
    }
}
