package com.example.app.adapters.outbound.persistence.repository;

import com.example.app.adapters.outbound.persistence.entity.BusinessAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessAddressJpaRepository extends JpaRepository<BusinessAddressEntity, String> {
}
