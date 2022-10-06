package com.example.militaryservicecompanychecker.company.service

import com.example.militaryservicecompanychecker.company.constants.GovernmentLocation
import com.example.militaryservicecompanychecker.company.constants.Sector
import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {
    fun searchCompany(searchName: String): List<Company> {
        return companyRepository.findByCompanyNameContaining(searchName)
    }

    fun searchCompany(
        searchName: String,
        governmentLocation: GovernmentLocation?,
        sector: Sector?
    ): List<Company> {
        val companies = companyRepository.findAll()

        return companies
            .filter {
                if (governmentLocation == null) true else it.governmentLocation == governmentLocation
            }.filter {
                if (sector == null) true else it.companySector == sector
            }
    }

    fun getGovernmentLocations(): Array<GovernmentLocation> {
        return GovernmentLocation.values()
    }

    fun getSectors(): Array<Sector> {
        return Sector.values()
    }
}