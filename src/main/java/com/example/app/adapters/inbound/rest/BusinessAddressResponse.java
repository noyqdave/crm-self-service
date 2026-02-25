package com.example.app.adapters.inbound.rest;

import com.example.app.domain.BusinessAddress;

/**
 * API response for a business address (street, city, stateProvince, postalCode, country).
 */
public record BusinessAddressResponse(
        String street,
        String city,
        String stateProvince,
        String postalCode,
        String country
) {
    static BusinessAddressResponse from(BusinessAddress address) {
        return new BusinessAddressResponse(
                address.getStreet(),
                address.getCity(),
                address.getStateProvince(),
                address.getPostalCode(),
                address.getCountry()
        );
    }
}
