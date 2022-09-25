package com.example.militaryservicecompanychecker.company

import com.example.militaryservicecompanychecker.company.controller.dto.Company
import com.example.militaryservicecompanychecker.company.util.Util.convertToServiceType
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val okHttpClient: OkHttpClient
) {
    fun searchCompany(searchName: String): List<Company> {
        return getCompanies(searchName)
    }

    fun getCompanies(searchName: String): List<Company> {
        val companies = ArrayList<Company>()

        for (serviceType in 1..3) {
            val basicElements = getElementsBySearchName(searchName, serviceType)

            basicElements.forEach {
                companies.add(
                    getCompanyByCompanyUrl(
                        it.attr("href"),
                        searchName,
                        serviceType.convertToServiceType()
                    )
                )
            }
        }

        return companies
    }

    private fun getCompanyByCompanyUrl(
        companyUrl: String,
        searchName: String,
        serviceType: String
    ): Company {
        val elements = getElementsByCompanyUrl(companyUrl)

        val companySummaryInformation = elements[0].select("tr")
        val companyDetailInformation = elements[1].select("tr")

        val companyName = companySummaryInformation[0].select("td").first()?.text()

        val companyLocation = companySummaryInformation[1].select("td").first()?.text()
        val companyPhoneNumber = companySummaryInformation[2].select("td")[0]?.text()
        val companyFaxNumber = companySummaryInformation[2].select("td")[1]?.text()

        val companySector = companyDetailInformation[0].select("td").first()?.text()
        val companyScale = companyDetailInformation[1].select("td").first()?.text()

        val companyKeyWord = getKeyWord(companyName, searchName)

        return Company(
            companyName,
            companyLocation,
            companyPhoneNumber,
            companyFaxNumber,
            companySector,
            companyScale,
            serviceType,
            companyKeyWord
        )
    }

    private fun getKeyWord(companyName: String?, searchName: String): String {
        val words = companyName?.split(" ") ?: return ""

        for (word in words) {
            if (word.contains(searchName)) {
                return word
                    .replace("(주)", "")
                    .replace("(유)", "")
                    .replace("주식회사", "")
            }
        }

        return companyName
    }

    private fun getElementsByCompanyUrl(companyUrl: String): Elements {
        val response = okHttpClient.newCall(
            Request.Builder()
                .url("https://work.mma.go.kr${companyUrl}")
                .get()
                .build()
        ).execute()

        val jsoup = Jsoup.parse(response.body?.string() ?: "")

        return jsoup.select("table.table_row")
    }

    private fun getElementsBySearchName(searchName: String, serviceType: Int): Elements {
        val formBody = FormBody.Builder()
            .add("al_eopjong_gbcd", "")
            .add("eopjong_gbcd_list", "")
            .add("eopjong_gbcd", "$serviceType")
            .add("gegyumo_cd", "")
            .add("eopche_nm", searchName)
            .add("sido_addr", "")
            .add("sigungu_addr", "")
            .build()

        val response = okHttpClient.newCall(
            Request.Builder()
                .url("https://work.mma.go.kr/caisBYIS/search/byjjecgeomsaek.do")
                .post(formBody)
                .build()
        ).execute()

        val jsoup = Jsoup.parse(response.body?.string() ?: "")

        return jsoup.select("a[href~=/caisBYIS/search/byjjecgeomsaekView(.*?)$]")
    }
}