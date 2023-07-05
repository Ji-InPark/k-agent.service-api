package com.example.militaryservicecompanychecker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MilitaryServiceCompanyCheckerApplication

fun main(args: Array<String>) {
    runApplication<MilitaryServiceCompanyCheckerApplication>(*args)
}
