
package com.ingsw.Petpal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerAPIConfig {

    @Value("${bookhub.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI getOpenAPI() {
        // Definir el servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("This is the Dev Server");

        // Informacion de Contacto
        Contact contact = new Contact();
        contact.setName("ingsw-test-dto-validation-exception");
        contact.setEmail("ingsw-test-dto-validation-exception@gmail.com");
        contact.setUrl("https://github.com/ingsw/test-dto-validation-exception");

        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        // Informacion General de la API
        Info info = new Info()
                .title("API Petal")
                .version("1.0")
                .contact(contact)
                .description("API Petpal")
                .termsOfService("https://github.com/ingsw/test-dto-validation-exception/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).addServersItem(devServer);
    }
}

