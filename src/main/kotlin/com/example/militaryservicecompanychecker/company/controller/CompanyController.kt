package com.example.militaryservicecompanychecker.company.controller

import com.example.militaryservicecompanychecker.company.CompanyService
import com.example.militaryservicecompanychecker.company.controller.dto.CompanyResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CompanyController(
    @Autowired
    private val companyService: CompanyService
) {
    @GetMapping("/search/{companyName}")
    fun searchCompany(@PathVariable("companyName") companyName: String): CompanyResponse {
        return CompanyResponse(companyService.searchCompany(companyName))
    }
}