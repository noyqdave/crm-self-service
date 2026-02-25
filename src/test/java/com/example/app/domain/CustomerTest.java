package com.example.app.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for Customer / address holder (checklist steps 4–6).
 */
@DisplayName("Customer")
class CustomerTest {

    private static final BusinessAddress EXAMPLE_ADDRESS =
            new BusinessAddress("100 Main St", "Springfield", "Illinois", "62701", "United States");

    @Nested
    @DisplayName("5. Can have one business address")
    class HasOneBusinessAddress {

        @Test
        @DisplayName("returning it yields the same value")
        void returningItYieldsTheSameValue() {
            var customer = new Customer(Optional.of(EXAMPLE_ADDRESS));

            var returned = customer.getBusinessAddress();

            assertThat(returned).contains(EXAMPLE_ADDRESS);
        }
    }
}
