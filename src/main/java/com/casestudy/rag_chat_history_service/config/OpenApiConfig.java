package com.casestudy.rag_chat_history_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi()
    {
        return  new OpenAPI()
                .info(
                        new Info()
                                .title("RAG Chat History Service")
                                .version("1.0")
                                .description("Production-ready RAG Chat History Microservice")
                                .contact(
                                        new Contact()
                                                .name("Harshitha")
                                                .email("harshitha.mc1895@gmail.com")
                                )
                );
    }
}
