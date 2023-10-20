package com.example.militaryservicecompanychecker.companyupdatehistory.repository

import com.example.militaryservicecompanychecker.companyupdatehistory.entity.CompanyUpdateHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CompanyUpdateHistoryRepository : JpaRepository<CompanyUpdateHistory, Long> {
    @Query("SELECT * FROM company_update_history ORDER BY DATE(history) DESC", nativeQuery = true)
    fun findAllOrderByHistory(): MutableList<CompanyUpdateHistory>
}