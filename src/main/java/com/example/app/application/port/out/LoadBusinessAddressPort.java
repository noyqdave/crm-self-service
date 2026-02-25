package com.example.app.application.port.out;

import com.example.app.domain.BusinessAddress;

import java.util.Optional;

/**
 * Outbound port: load the canonical business address for a customer.
 */
public interface LoadBusinessAddressPort {

    Optional<BusinessAddress> load(String customerId);
}
