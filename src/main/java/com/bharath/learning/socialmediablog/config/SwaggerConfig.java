package com.bharath.learning.socialmediablog.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Social Media Blog API",
        version = "v1.0",
        description = "This API allows users to create, fetch, update, and delete posts and comments on a social media blog platform.",
        license = @License(name = "MIT", url = "https://opensource.org/licenses/MIT"),
        contact = @Contact(
            name = "Bharath Ashok",
            email = "iambharath.ashoka@gmail.com",
            url = "https://bharathportfolio.com"
        )
    ),
    servers = @Server(url = "http://localhost:8080", description = "Local Server")
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi postsApi() {
        return GroupedOpenApi.builder()
            .group("Posts API")
            .packagesToScan("com.bharath.learning.socialmediablog.controller")
            .build();
    }

    @Bean
    public GroupedOpenApi commentsApi() {
        return GroupedOpenApi.builder()
            .group("Comments API")
            .packagesToScan("com.bharath.learning.socialmediablog.controller")
            .build();
    }
}
