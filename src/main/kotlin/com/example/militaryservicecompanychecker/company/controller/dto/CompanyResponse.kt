package com.example.militaryservicecompanychecker.company.controller.dto

import com.example.militaryservicecompanychecker.company.entity.Company

data class CompanyResponse(val companies: List<Company>) {
    val companyCount get() = companies.count()
}