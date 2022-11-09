package com.example.militaryservicecompanychecker.company.controller.dto

data class CompanyAutoCompleteResponse(
    val companies: List<String>
) {
    val companyCount get() = companies.count()
}