package com.example.app.domain;

/**
 * Value object representing a canonical business address (street, city, state/province, postal code, country).
 * All fields are mandatory and freeform.
 */
public final class BusinessAddress {

    private final String street;
    private final String city;
    private final String stateProvince;
    private final String postalCode;
    private final String country;

    public BusinessAddress(String street, String city, String stateProvince, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.stateProvince = stateProvince;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }
}
