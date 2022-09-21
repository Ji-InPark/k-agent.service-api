package com.example.militaryservicecompanrychecker.company

import com.example.militaryservicecompanrychecker.company.controller.dto.Company
import com.example.militaryservicecompanrychecker.company.util.Util.convertToServiceCategory
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val okHttpClient: OkHttpClient
) {
    fun searchCompany(companyName: String): List<Company> {
        val nameUrlMap = getCompanyNameUrlMap(companyName)

        return getCompanyInformationListByMap(nameUrlMap)
    }

    fun getCompanyInformationListByMap(nameUrlMap: HashMap<Pair<String, Int>, String>): List<Company> {
        val map = HashMap<String?, Company>()

        for (entry in nameUrlMap.entries) {
            val response = okHttpClient.newCall(
                Request.Builder()
                    .url("https://work.mma.go.kr${entry.value}")
                    .get()
                    .build()
            ).execute()

            val jsoup = Jsoup.parse(response.body?.string() ?: "")

            val elements = jsoup.select("table.table_row")

            val companySummaryInformation = elements[0].select("tr")
            val companyDetailInformation = elements[1].select("tr")

            val companyName = companySummaryInformation[0].select("td").first()?.text()
            val companyLocation = companySummaryInformation[1].select("td").first()?.text()
            val companyPhoneNumber = companySummaryInformation[2].select("td")[0]?.text()
            val companyFaxNumber = companySummaryInformation[2].select("td")[1]?.text()

            val companySector = companyDetailInformation[0].select("td").first()?.text()
            val companyScale = companyDetailInformation[1].select("td").first()?.text()

            if (map.containsKey(companyFaxNumber)) {
                map[companyFaxNumber]!!.companyName.add(companyName)
                map[companyFaxNumber]!!.serviceTypes.add(entry.key.second.convertToServiceCategory())
                continue
            }

            val company = Company(
                ArrayList(),
                companyLocation,
                companyPhoneNumber,
                companyFaxNumber,
                companySector,
                companyScale,
                ArrayList()
            )
            
            company.companyName.add(companyName)
            company.serviceTypes.add(entry.key.second.convertToServiceCategory())

            map[companyFaxNumber] = company
        }

        return map.entries.map { it.value }
    }

    fun getCompanyNameUrlMap(companyName: String): HashMap<Pair<String, Int>, String> {
        val map = HashMap<Pair<String, Int>, String>()

        for (i in 1..3) {
            val formBody = FormBody.Builder()
                .add("al_eopjong_gbcd", "")
                .add("eopjong_gbcd_list", "")
                .add("eopjong_gbcd", "$i")
                .add("gegyumo_cd", "")
                .add("eopche_nm", companyName)
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

            val elements = jsoup.select("a[href~=/caisBYIS/search/byjjecgeomsaekView(.*?)$]")

            elements.forEach {
                if (!map.containsKey(Pair(it.text(), i)))
                    map[Pair(it.text(), i)] = it.attr("href")
            }
        }

        return map
    }
}