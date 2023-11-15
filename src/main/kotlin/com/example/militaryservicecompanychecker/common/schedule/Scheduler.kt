package com.example.militaryservicecompanychecker.common.schedule

import com.example.militaryservicecompanychecker.company.service.CompanyService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Scheduler(
    private val companyService: CompanyService
) {
    @Scheduled(cron = "* * 3 * * *", zone = "Asia/Seoul")
    fun updateCompany() {
        companyService.updateCompanyInfoByBYIS()
    }
}