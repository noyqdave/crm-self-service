package com.example.app.application.port.out;

import com.example.app.domain.BusinessAddress;

/**
 * Outbound port: save the canonical business address for a customer (insert or overwrite).
 */
public interface SaveBusinessAddressPort {

    void save(String customerId, BusinessAddress address);
}
