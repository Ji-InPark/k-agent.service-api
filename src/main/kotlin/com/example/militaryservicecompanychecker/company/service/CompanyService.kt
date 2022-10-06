package com.example.militaryservicecompanychecker.company.service

import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.repository.CompanyRepository
import okhttp3.OkHttpClient
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val okHttpClient: OkHttpClient,
    private val companyRepository: CompanyRepository
) {
    fun searchCompany(searchName: String): List<Company> {
        return companyRepository.findByCompanyNameContaining(searchName)
    }
}