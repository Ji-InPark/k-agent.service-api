package com.example.militaryservicecompanychecker.companyupdatehistory.controller

import com.example.militaryservicecompanychecker.companyupdatehistory.entity.CompanyUpdateHistory
import com.example.militaryservicecompanychecker.companyupdatehistory.service.CompanyUpdateHistoryService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["https://k-agent.services/", "http://localhost:3000/"])
class CompanyUpdateHistoryController(
    private val companyUpdateHistoryService: CompanyUpdateHistoryService
) {

    @GetMapping("/company-update-history")
    fun getCompanyUpdateHistory(): MutableList<CompanyUpdateHistory> {
        return companyUpdateHistoryService.getAllCompanyUpdateHistory()
    }
}