package com.ingsw.petpal.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;

// Asegurando que se haya hecho merge correctamente
@Configuration
public class SwaggerAPIConfig {

    @Value("${petpal.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI getOpenAPI() {
        // Definir el servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("This is the Dev Server");

        // Informacion de Contacto
        Contact contact = new Contact();
        contact.setName("petpal-community");
        contact.setEmail("petpalcommnutiy@gmail.com");
        contact.setUrl("https://github.com/ingsw/petpal");

        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        // Informacion General de la API
        Info info = new Info()
                .title("API petpal")
                .version("1.0")
                .contact(contact)
                .description("API de petpal community")
                .termsOfService("https://github.com/ingsw/test-invoice/terms")
                .license(mitLicense);


        // Configuración de seguridad JWT
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("JWT Authentication");

        Components components = new Components()
                .addSecuritySchemes("bearerAuth", securityScheme);

        // Requerimiento de seguridad para utilizar en las operaciones
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");



        return new OpenAPI()
                .info(info)
                //.addServersItem(devServer);
                .servers(List.of(devServer))
                .addSecurityItem(securityRequirement)
                .components(components);
    }

}