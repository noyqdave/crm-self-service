package com.example.app.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for BusinessAddress value object (checklist steps 1–3).
 */
@DisplayName("BusinessAddress")
class BusinessAddressTest {

    private static final String STREET = "100 Main St";
    private static final String CITY = "Springfield";
    private static final String STATE_PROVINCE = "Illinois";
    private static final String POSTAL_CODE = "62701";
    private static final String COUNTRY = "United States";

    @Nested
    @DisplayName("1. Create with all five fields")
    class Creation {

        @Test
        @DisplayName("creation succeeds and holds values")
        void creationSucceedsAndHoldsValues() {
            var address = new BusinessAddress(STREET, CITY, STATE_PROVINCE, POSTAL_CODE, COUNTRY);

            assertThat(address.getStreet()).isEqualTo(STREET);
            assertThat(address.getCity()).isEqualTo(CITY);
            assertThat(address.getStateProvince()).isEqualTo(STATE_PROVINCE);
            assertThat(address.getPostalCode()).isEqualTo(POSTAL_CODE);
            assertThat(address.getCountry()).isEqualTo(COUNTRY);
        }
    }
}
