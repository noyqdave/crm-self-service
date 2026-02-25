package com.example.app.application;

import com.example.app.application.port.out.LoadBusinessAddressPort;
import com.example.app.domain.BusinessAddress;

import java.util.Optional;

/**
 * Use case: get the current business address for a customer.
 */
public class GetCurrentBusinessAddressUseCase {

    private final LoadBusinessAddressPort loadBusinessAddressPort;

    public GetCurrentBusinessAddressUseCase(LoadBusinessAddressPort loadBusinessAddressPort) {
        this.loadBusinessAddressPort = loadBusinessAddressPort;
    }

    public Optional<BusinessAddress> getCurrentBusinessAddress(String customerId) {
        return loadBusinessAddressPort.load(customerId);
    }
}
