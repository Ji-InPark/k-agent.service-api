package com.example.militaryservicecompanychecker

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {
    @Bean
    fun getOpenAPI(): OpenAPI {
        return OpenAPI()
            .servers(listOf(Server().apply { url = "/api/" }))
            .info(
                Info().title("Military-Checker-api")
                    .description("병역특례 업체 확인 API")
                    .version("v0.0.0")
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("병역특례 업체 확인 API")
            )
    }
}