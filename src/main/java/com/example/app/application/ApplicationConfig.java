package com.example.app.application;

import com.example.app.application.port.out.LoadBusinessAddressPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public GetCurrentBusinessAddressUseCase getCurrentBusinessAddressUseCase(LoadBusinessAddressPort loadBusinessAddressPort) {
        return new GetCurrentBusinessAddressUseCase(loadBusinessAddressPort);
    }
}
