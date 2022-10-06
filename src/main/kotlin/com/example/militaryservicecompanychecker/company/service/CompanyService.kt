package com.example.militaryservicecompanychecker.company.service

import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.repository.CompanyRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.boot.json.GsonJsonParser
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val okHttpClient: OkHttpClient,
    private val companyRepository: CompanyRepository
) {
    fun searchCompany(searchName: String): List<Company> {
        return getCompanies(searchName)
    }

    fun getCompanies(searchName: String): List<Company> {
        val companies = companyRepository.findTop30ByCompanyNameContaining(searchName)

        companies.forEach { it.apply { kreditJobKey = getKreditJobKey(companyKeyword.toString()) } }

        return companies
    }

    private fun getKreditJobKey(companyKeyWord: String): String {
        val body =
            """{"q": "$companyKeyWord"}""".toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val response = okHttpClient.newCall(
            Request.Builder()
                .url("https://kreditjob.com/api/search/company")
                .post(body)
                .build()
        ).execute()

        return GsonJsonParser().parseMap(response.body?.string())["PK_NM_HASH"].toString()
    }
}