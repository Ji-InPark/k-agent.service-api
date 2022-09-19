package com.example.militaryservicecompanrychecker.company.controller

import com.example.militaryservicecompanrychecker.company.CompanyService
import com.example.militaryservicecompanrychecker.company.controller.dto.CompanyRequest
import com.example.militaryservicecompanrychecker.company.controller.dto.CompanyResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CompanyController(
    @Autowired
    private val companyService: CompanyService
) {
    @PostMapping("/companies")
    fun searchCompany(@RequestBody request: CompanyRequest): CompanyResponse {
        return CompanyResponse(companyService.searchCompany(request.companyName))
    }
}