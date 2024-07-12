package com.example.militaryservicecompanychecker.company.controller.dto

data class CompanyRequest(
    val companyName: String?,
    val governmentLocation: String?,
    val sector: String?,
    val page: Int,
    val size: Int = 10
)