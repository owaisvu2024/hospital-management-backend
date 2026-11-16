package com.hospital.demo.My_Hospital_Mangment_App.Security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI hospitalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Hospital Management System API")
                        .version("1.0")
                        .description("REST APIs documentation for Hospital Management System backend."))

                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components( new Components()

                        .addSecuritySchemes("BearerAuth",new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                                .bearerFormat("JWT")
                                        .description("Yahan apna JWT Token paste karein (Example: eyJhbGciOi...)")

                        )
                );
    }


}
