package com.aluracursos.foro_hub.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("API Foro Hub Challenge")
                        .description("API Rest del Foro Hub Challenge. Contiene las funcionalidades de CRUD de tópicos.")
                        .contact(new Contact()
                                .name("Alexis González")
                                .email("Alexis.gonzalez_@outlook.com")));
//                        .license(new License()
//                                .name("Apache 2.0")
//                                .url("http://foro.hub.challenge/api/licencia")));
    }
}
