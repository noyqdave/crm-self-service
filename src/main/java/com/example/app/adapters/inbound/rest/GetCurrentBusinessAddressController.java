package com.example.app.adapters.inbound.rest;

import com.example.app.application.GetCurrentBusinessAddressUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST adapter: GET current business address for the customer ("me").
 */
@RestController
@RequestMapping("/api/customers/me")
public class GetCurrentBusinessAddressController {

    private static final String CURRENT_CUSTOMER_ID = "me";

    private final GetCurrentBusinessAddressUseCase getCurrentBusinessAddressUseCase;

    public GetCurrentBusinessAddressController(GetCurrentBusinessAddressUseCase getCurrentBusinessAddressUseCase) {
        this.getCurrentBusinessAddressUseCase = getCurrentBusinessAddressUseCase;
    }

    @GetMapping("/business-address")
    public ResponseEntity<BusinessAddressResponse> getCurrentBusinessAddress() {
        return getCurrentBusinessAddressUseCase.getCurrentBusinessAddress(CURRENT_CUSTOMER_ID)
                .map(BusinessAddressResponse::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
