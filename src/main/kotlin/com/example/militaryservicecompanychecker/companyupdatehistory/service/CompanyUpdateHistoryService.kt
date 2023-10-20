package com.example.militaryservicecompanychecker.companyupdatehistory.service

import com.example.militaryservicecompanychecker.companyupdatehistory.entity.CompanyUpdateHistory
import com.example.militaryservicecompanychecker.companyupdatehistory.repository.CompanyUpdateHistoryRepository
import org.springframework.stereotype.Service

@Service
class CompanyUpdateHistoryService(
    private val companyUpdateHistoryRepository: CompanyUpdateHistoryRepository
) {
    fun getAllCompanyUpdateHistory(): MutableList<CompanyUpdateHistory> {
        return companyUpdateHistoryRepository.findAllOrderByHistory()
    }
}