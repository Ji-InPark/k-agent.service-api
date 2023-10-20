package com.example.militaryservicecompanychecker.companyupdatehistory.service

import com.example.militaryservicecompanychecker.companyupdatehistory.entity.CompanyUpdateHistory
import com.example.militaryservicecompanychecker.companyupdatehistory.repository.CompanyUpdateHistoryRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CompanyUpdateHistoryService(
    private val companyUpdateHistoryRepository: CompanyUpdateHistoryRepository
) {
    fun getAllCompanyUpdateHistory(): MutableList<CompanyUpdateHistory> {
        return companyUpdateHistoryRepository.findAllOrderByHistory()
    }

    fun addCompanyUpdateHistory(history: LocalDate) {
        companyUpdateHistoryRepository.saveAndFlush(CompanyUpdateHistory(history))
    }
}