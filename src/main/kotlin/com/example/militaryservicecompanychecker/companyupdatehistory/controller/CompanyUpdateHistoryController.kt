package com.example.militaryservicecompanychecker.companyupdatehistory.controller

import com.example.militaryservicecompanychecker.companyupdatehistory.entity.CompanyUpdateHistory
import com.example.militaryservicecompanychecker.companyupdatehistory.service.CompanyUpdateHistoryService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CompanyUpdateHistoryController(
    private val companyUpdateHistoryService: CompanyUpdateHistoryService
) {

    @GetMapping("/company-update-history")
    fun getCompanyUpdateHistory(): MutableList<CompanyUpdateHistory> {
        return companyUpdateHistoryService.getAllCompanyUpdateHistory()
    }
}