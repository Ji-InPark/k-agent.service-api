package com.example.militaryservicecompanrychecker.company.controller.dto

data class Company(
    val companyName: ArrayList<String?>,
    val companyLocation: String?,
    val companyPhoneNumber: String?,
    val companyFaxNumber: String?,
    val companySector: String?,
    val companyScale: String?,
    val serviceTypes: ArrayList<String>,
)