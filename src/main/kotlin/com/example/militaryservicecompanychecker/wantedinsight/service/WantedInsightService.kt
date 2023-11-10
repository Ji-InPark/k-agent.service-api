package com.example.militaryservicecompanychecker.wantedinsight.service

import com.example.militaryservicecompanychecker.common.service.ApiService
import com.google.gson.internal.LinkedTreeMap
import org.springframework.boot.json.GsonJsonParser
import org.springframework.stereotype.Service

@Service
class WantedInsightService(
    private val apiService: ApiService
) {
    fun getWantedInsightKey(companyKeyword: String): String? {
        val url = "https://insight.wanted.co.kr/api/search/autocomplete"
        val urlQuery = "q=$companyKeyword&index=0&size=5"
        val response = apiService.get(url, urlQuery)

        return try {
            val docs = GsonJsonParser().parseMap(response.body?.string())["docs"] as ArrayList<*>
            val companyInfo = docs[0] as LinkedTreeMap<*, *>
            companyInfo["regNoHash"]?.toString()
        } catch (e: Exception) {
            null
        }
    }
}