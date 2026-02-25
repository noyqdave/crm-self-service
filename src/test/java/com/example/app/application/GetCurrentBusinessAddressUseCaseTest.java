package com.example.app.application;

import com.example.app.application.port.out.LoadBusinessAddressPort;
import com.example.app.domain.BusinessAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for Get current business address use case (checklist steps 7–9).
 */
@DisplayName("GetCurrentBusinessAddressUseCase")
class GetCurrentBusinessAddressUseCaseTest {

    private static final String CUSTOMER_ID = "cust-1";
    private static final BusinessAddress EXAMPLE_ADDRESS =
            new BusinessAddress("100 Main St", "Springfield", "Illinois", "62701", "United States");

    @Nested
    @DisplayName("7. When outbound port returns an address")
    class PortReturnsAddress {

        @Test
        @DisplayName("use case returns that address (success)")
        void useCaseReturnsThatAddress() {
            var loadPort = mock(LoadBusinessAddressPort.class);
            given(loadPort.load(CUSTOMER_ID)).willReturn(Optional.of(EXAMPLE_ADDRESS));

            var useCase = new GetCurrentBusinessAddressUseCase(loadPort);
            var result = useCase.getCurrentBusinessAddress(CUSTOMER_ID);

            assertThat(result).contains(EXAMPLE_ADDRESS);
        }
    }
}
