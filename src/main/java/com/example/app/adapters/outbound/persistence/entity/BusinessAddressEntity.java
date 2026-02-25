package com.example.app.adapters.outbound.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "business_address")
public class BusinessAddressEntity {

    @Id
    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(name = "state_province", nullable = false)
    private String stateProvince;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String country;

    protected BusinessAddressEntity() {
    }

    public BusinessAddressEntity(String customerId, String street, String city, String stateProvince, String postalCode, String country) {
        this.customerId = customerId;
        this.street = street;
        this.city = city;
        this.stateProvince = stateProvince;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getCustomerId() {
        return customerId;
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

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
