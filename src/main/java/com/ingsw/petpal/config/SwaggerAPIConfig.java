package com.ingsw.petpal.config;

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

    // Puedes establecer la URL de tu servidor de desarrollo en application.properties
    @Value("http://localhost:8080/api/v2")
    private String devUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        // Definir el servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Servidor de desarrollo para PetPal API");

        // Información de contacto del desarrollador o responsable de la API
        Contact contact = new Contact();
        contact.setName("PetPal Team");
        contact.setEmail("contacto@petpal.com");
        contact.setUrl("https://github.com/PetPal-Community/PetPal-BackEnd");

        // Definir la licencia de la API (puedes cambiarla según tus necesidades)
        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        // Información general sobre la API
        Info apiInfo = new Info()
                .title("PetPal API")
                .version("1.0")
                .contact(contact)
                .description("API para la gestión de mascotas, recordatorios y más en PetPal")
                .termsOfService("https://github.com/PetPal-Community/PetPal-BackEnd/terms")
                .license(mitLicense);

        // Crear y retornar el objeto OpenAPI con la configuración
        return new OpenAPI().info(apiInfo).addServersItem(devServer);
    }
}