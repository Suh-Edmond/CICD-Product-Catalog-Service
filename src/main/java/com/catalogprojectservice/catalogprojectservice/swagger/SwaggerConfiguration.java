package com.catalogprojectservice.catalogprojectservice.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod")
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Product Catalog Service").version("1.0.0"))
                .components(new Components()
                        .addSecuritySchemes("Authorization", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .in(SecurityScheme.In.HEADER)
                                .scheme("Bearer")
                                .name("Authorization")))
                .addSecurityItem(new SecurityRequirement().addList("Authorization"));
    }

}