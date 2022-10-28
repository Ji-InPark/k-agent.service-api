package com.example.militaryservicecompanychecker.company.service

import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector
import com.example.militaryservicecompanychecker.company.repository.CompanyRepository
import com.example.militaryservicecompanychecker.company.repository.CustomCompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
    private val customCompanyRepository: CustomCompanyRepository,
) {
    fun searchCompany(searchName: String): List<Company> {
        return companyRepository.findByCompanyNameContaining(searchName)
    }

    fun searchCompany(
        searchName: String,
        governmentLocation: GovernmentLocation?,
        sector: Sector?
    ): List<Company> {
        return customCompanyRepository.findCompaniesByNameAndSectorAndGovernmentLocation(
            searchName,
            governmentLocation,
            sector
        )
    }

    fun getGovernmentLocations(): Array<GovernmentLocation> {
        return GovernmentLocation.values()
    }

    fun getSectors(): Array<Sector> {
        return Sector.values()
    }
}