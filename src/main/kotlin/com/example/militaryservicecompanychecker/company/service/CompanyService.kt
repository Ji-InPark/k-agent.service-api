package com.example.militaryservicecompanychecker.company.service

import com.example.militaryservicecompanychecker.byis.service.ByisService
import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector
import com.example.militaryservicecompanychecker.company.enums.ServiceType
import com.example.militaryservicecompanychecker.company.repository.CompanyRepository
import com.example.militaryservicecompanychecker.company.repository.CustomCompanyRepository
import com.example.militaryservicecompanychecker.companyupdatehistory.service.CompanyUpdateHistoryService
import com.example.militaryservicecompanychecker.wantedinsight.service.WantedInsightService
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
    private val customCompanyRepository: CustomCompanyRepository,
    private val byisService: ByisService,
    private val wantedInsightService: WantedInsightService,
    private val companyUpdateHistoryService: CompanyUpdateHistoryService
) {
    fun searchCompanyByRegex(regex: String): List<Company> {
        return companyRepository.findTop5ByCompanyNameRegex(regex)
    }

    fun searchCompany(
        searchName: String,
        governmentLocation: GovernmentLocation?,
        sector: Sector?,
        page: Int,
        size: Int
    ): PageImpl<Company> {
        val pageRequest = PageRequest.of(page, size)

        val companies = customCompanyRepository.findAllByCondition(
            searchName,
            sector,
            governmentLocation,
            pageRequest
        )

        val counts = customCompanyRepository.countAllByCondition(
            searchName,
            sector,
            governmentLocation
        )

        return PageImpl(companies, pageRequest, counts)
    }

    fun getGovernmentLocations(): Array<GovernmentLocation> {
        return GovernmentLocation.values()
    }

    fun getSectors(): Array<Sector> {
        return Sector.values()
    }

    fun getOrRequestWantedInsightKey(id: Long): String? {
        val company = companyRepository.findById(id).orElseThrow()

        if (company.wantedInsightKey != null) return company.wantedInsightKey

        val wantedInsightKey = wantedInsightService.getWantedInsightKey(company.companyKeyword)
        company.wantedInsightKey = wantedInsightKey
        companyRepository.saveAndFlush(company)

        return wantedInsightKey
    }

    @Transactional
    fun updateCompanyInfoByBYIS(): MutableList<Company> {
        companyRepository.deleteAllInBatch()

        val companies = byisService.extractCompanies()

        companyUpdateHistoryService.addCompanyUpdateHistory(LocalDate.now())

        return companyRepository.saveAllAndFlush(companies)
    }


    fun getServiceTypes(): Array<ServiceType> {
        return ServiceType.values()
    }
}