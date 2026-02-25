package com.example.app.adapters.inbound.rest;

import com.example.app.application.GetCurrentBusinessAddressUseCase;
import com.example.app.domain.BusinessAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Controller tests for get current business address (checklist steps 10–12).
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ContextConfiguration(classes = GetCurrentBusinessAddressControllerTest.WebLayerConfig.class)
@DisplayName("GetCurrentBusinessAddressController")
class GetCurrentBusinessAddressControllerTest {

    @Configuration
    @Import({WebMvcAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class})
    static class WebLayerConfig {
        @Bean
        GetCurrentBusinessAddressUseCase getCurrentBusinessAddressUseCase() {
            return mock(GetCurrentBusinessAddressUseCase.class);
        }

        @Bean
        GetCurrentBusinessAddressController getCurrentBusinessAddressController(GetCurrentBusinessAddressUseCase useCase) {
            return new GetCurrentBusinessAddressController(useCase);
        }
    }

    @Autowired
    MockMvc mockMvc;

    @Autowired
    GetCurrentBusinessAddressUseCase getCurrentBusinessAddressUseCase;

    private static final BusinessAddress EXAMPLE_ADDRESS =
            new BusinessAddress("100 Main St", "Springfield", "Illinois", "62701", "United States");

    @Nested
    @DisplayName("10. When use case returns an address")
    class UseCaseReturnsAddress {

        @Test
        @DisplayName("response status is 200 and body is JSON with street, city, stateProvince, postalCode, country")
        void responseIs200WithJsonBody() throws Exception {
            given(getCurrentBusinessAddressUseCase.getCurrentBusinessAddress("me"))
                    .willReturn(Optional.of(EXAMPLE_ADDRESS));

            mockMvc.perform(get("/api/customers/me/business-address").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.street").value("100 Main St"))
                    .andExpect(jsonPath("$.city").value("Springfield"))
                    .andExpect(jsonPath("$.stateProvince").value("Illinois"))
                    .andExpect(jsonPath("$.postalCode").value("62701"))
                    .andExpect(jsonPath("$.country").value("United States"));
        }
    }
}
