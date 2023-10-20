package com.example.militaryservicecompanychecker.company.service

import com.example.militaryservicecompanychecker.byis.service.ByisService
import com.example.militaryservicecompanychecker.common.service.ApiService
import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector
import com.example.militaryservicecompanychecker.company.enums.ServiceType
import com.example.militaryservicecompanychecker.company.repository.CompanyRepository
import com.google.gson.internal.LinkedTreeMap
import org.springframework.boot.json.GsonJsonParser
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
    private val apiService: ApiService,
    private val byisService: ByisService
) {
    fun searchCompanyByRegex(regex: String): List<Company> {
        return companyRepository.findTop5ByCompanyNameRegex(regex)
    }

    fun searchCompany(
        searchName: String,
        governmentLocation: GovernmentLocation?,
        sector: Sector?
    ): List<Company> {
        return if (governmentLocation == null && sector == null)
            companyRepository.findAllByCompanyNameContainsOrderByIdAsc(searchName)
        else
            companyRepository.findAllByGovernmentLocationOrCompanySectorAndCompanyName(
                searchName,
                governmentLocation?.toString(),
                sector?.toString()
            )
    }

    fun getGovernmentLocations(): Array<GovernmentLocation> {
        return GovernmentLocation.values()
    }

    fun getSectors(): Array<Sector> {
        return Sector.values()
    }

    fun getWantedInsightKeyAndUpdateToCompany(id: Long): String? {
        val company = companyRepository.findById(id).orElseThrow()

        if (company.wantedInsightKey != null) return company.wantedInsightKey

        val wantedInsightKey = wantedInsightJobKey(company.companyKeyword)
        company.wantedInsightKey = wantedInsightKey
        companyRepository.saveAndFlush(company)

        return wantedInsightKey
    }

    private fun wantedInsightJobKey(companyKeyword: String): String? {
        val url = "https://insight.wanted.co.kr/api/search/autocomplete"
        val urlQuery = "q=$companyKeyword&index=0&size=5"
        val response = apiService.get(url, urlQuery)

        val docs = GsonJsonParser().parseMap(response.body?.string())["docs"] as ArrayList<*>
        val companyInfo = docs[0] as LinkedTreeMap<*, *>
        return companyInfo["regNoHash"]?.toString()
    }

    @Transactional
    fun updateCompanyInfoByBYIS(): MutableList<Company> {
        companyRepository.deleteAllInBatch()

        val companies = byisService.extractCompanies()

        return companyRepository.saveAllAndFlush(companies)
    }


    fun getServiceTypes(): Array<ServiceType> {
        return ServiceType.values()
    }
}