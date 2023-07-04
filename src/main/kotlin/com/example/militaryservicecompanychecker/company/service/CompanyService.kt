package com.example.militaryservicecompanychecker.company.service

import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector
import com.example.militaryservicecompanychecker.company.repository.CompanyRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.boot.json.GsonJsonParser
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository,
    private val okHttpClient: OkHttpClient
) {
    fun searchCompanyByRegex(regex: String): List<Company> {
        return companyRepository.findTop5ByCompanyNameRegex(regex)
    }

    fun searchCompany(
        searchName: String,
        governmentLocation: GovernmentLocation?,
        sector: Sector?
    ): List<Company> {
        return companyRepository.findAllByGovernmentLocationOrCompanySectorAndCompanyName(
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

    fun getKreditJobKeyAndUpdateToCompany(id: Long): String {
        val company = companyRepository.findById(id).orElseThrow()

        if (company.kreditJobKey != null) return company.kreditJobKey!!

        val kreditJobKey = getKreditJobKey(company.companyName)
        company.kreditJobKey = kreditJobKey
        companyRepository.saveAndFlush(company)

        return kreditJobKey
    }

    private fun getKreditJobKey(companyKeyword: String): String {
        val body =
            """{"q": "$companyKeyword"}""".toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val response = okHttpClient.newCall(
            Request.Builder()
                .url("https://kreditjob.com/api/search/company")
                .post(body)
                .build()
        ).execute()

        return GsonJsonParser().parseMap(response.body?.string())["PK_NM_HASH"].toString()
    }
}