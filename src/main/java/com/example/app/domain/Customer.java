package com.example.app.domain;

import java.util.Optional;

/**
 * Domain entity representing a customer. Holds one canonical optional business address.
 */
public final class Customer {

    private final Optional<BusinessAddress> businessAddress;

    public Customer(Optional<BusinessAddress> businessAddress) {
        this.businessAddress = businessAddress;
    }

    public Optional<BusinessAddress> getBusinessAddress() {
        return businessAddress;
    }
}
