package com.example.app.adapters.outbound.persistence;

import com.example.app.application.port.out.LoadBusinessAddressPort;
import com.example.app.application.port.out.SaveBusinessAddressPort;
import com.example.app.domain.BusinessAddress;
import com.example.app.adapters.outbound.persistence.entity.BusinessAddressEntity;
import com.example.app.adapters.outbound.persistence.repository.BusinessAddressJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Persistence adapter: load and save business address via JPA.
 */
@Component
public class BusinessAddressPersistenceAdapter implements LoadBusinessAddressPort, SaveBusinessAddressPort {

    private final BusinessAddressJpaRepository jpaRepository;

    public BusinessAddressPersistenceAdapter(BusinessAddressJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<BusinessAddress> load(String customerId) {
        return jpaRepository.findById(customerId)
                .map(this::toDomain);
    }

    @Override
    public void save(String customerId, BusinessAddress address) {
        var entity = jpaRepository.findById(customerId)
                .orElse(new BusinessAddressEntity(customerId, address.getStreet(), address.getCity(),
                        address.getStateProvince(), address.getPostalCode(), address.getCountry()));
        entity.setStreet(address.getStreet());
        entity.setCity(address.getCity());
        entity.setStateProvince(address.getStateProvince());
        entity.setPostalCode(address.getPostalCode());
        entity.setCountry(address.getCountry());
        jpaRepository.save(entity);
    }

    private BusinessAddress toDomain(BusinessAddressEntity entity) {
        return new BusinessAddress(
                entity.getStreet(),
                entity.getCity(),
                entity.getStateProvince(),
                entity.getPostalCode(),
                entity.getCountry()
        );
    }
}
