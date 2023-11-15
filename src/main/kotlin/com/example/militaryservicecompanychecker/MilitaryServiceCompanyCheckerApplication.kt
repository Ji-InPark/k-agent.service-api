package com.example.militaryservicecompanychecker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableJpaAuditing
@EnableScheduling
class MilitaryServiceCompanyCheckerApplication

fun main(args: Array<String>) {
    runApplication<MilitaryServiceCompanyCheckerApplication>(*args)
}
