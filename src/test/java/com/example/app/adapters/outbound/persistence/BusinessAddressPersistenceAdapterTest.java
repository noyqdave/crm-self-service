package com.example.app.adapters.outbound.persistence;

import com.example.app.application.port.out.LoadBusinessAddressPort;
import com.example.app.application.port.out.SaveBusinessAddressPort;
import com.example.app.domain.BusinessAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for business address persistence (checklist steps 13–15).
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
@DisplayName("BusinessAddressPersistenceAdapter")
class BusinessAddressPersistenceAdapterTest {

    @Autowired
    SaveBusinessAddressPort saveBusinessAddressPort;

    @Autowired
    LoadBusinessAddressPort loadBusinessAddressPort;

    private static final String CUSTOMER_ID = "me";
    private static final BusinessAddress EXAMPLE_ADDRESS =
            new BusinessAddress("100 Main St", "Springfield", "Illinois", "62701", "United States");

    @Nested
    @DisplayName("13. Save a business address for a customer")
    class SaveAndLoad {

        @Test
        @DisplayName("load returns the same address")
        void loadReturnsTheSameAddress() {
            saveBusinessAddressPort.save(CUSTOMER_ID, EXAMPLE_ADDRESS);

            var loaded = loadBusinessAddressPort.load(CUSTOMER_ID);

            assertThat(loaded).isPresent();
            assertThat(loaded.get().getStreet()).isEqualTo(EXAMPLE_ADDRESS.getStreet());
            assertThat(loaded.get().getCity()).isEqualTo(EXAMPLE_ADDRESS.getCity());
            assertThat(loaded.get().getStateProvince()).isEqualTo(EXAMPLE_ADDRESS.getStateProvince());
            assertThat(loaded.get().getPostalCode()).isEqualTo(EXAMPLE_ADDRESS.getPostalCode());
            assertThat(loaded.get().getCountry()).isEqualTo(EXAMPLE_ADDRESS.getCountry());
        }
    }
}
