package com.example.militaryservicecompanychecker.company.repository

import com.example.militaryservicecompanychecker.company.entity.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, String> {
    fun findTop30ByCompanyNameContaining(companyName: String): List<Company>
}