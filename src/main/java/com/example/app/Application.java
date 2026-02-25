package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.app.adapters.outbound.persistence.entity")
@EnableJpaRepositories("com.example.app.adapters.outbound.persistence.repository")
@ComponentScan(
        basePackages = "com.example.app",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*GetCurrentBusinessAddressControllerTest\\$.*"
        )
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
