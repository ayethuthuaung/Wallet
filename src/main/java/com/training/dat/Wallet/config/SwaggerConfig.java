package com.training.dat.Wallet.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Wallet Service API",
        version = "1.0",
        description = "API documentation for the Wallet Service",
        contact = @Contact(name = "Support Team", email = "support@walletservice.com"),
        license = @License(name = "Apache 2.0", url = "http://springdoc.org")
    ),
    servers = @Server(url = "http://localhost:8080")
)
public class SwaggerConfig {
    // Additional configuration can be added here if needed
}
