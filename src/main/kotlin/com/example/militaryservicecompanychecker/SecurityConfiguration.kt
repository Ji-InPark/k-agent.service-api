package com.example.militaryservicecompanychecker

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class SecurityConfiguration {
    @Bean("passwordEncoder")
    fun passwordEncoder() = BCryptPasswordEncoder()
}