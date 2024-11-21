package com.ng.NgBank.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiconfig {


    @Bean
    public OpenAPI createOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Ng Bank")
                .description("A Rest Controller for the Banking " + "App")
                .contact(
                        new Contact().email("gyawalineeraj777@gmail.com")));
    }



}
