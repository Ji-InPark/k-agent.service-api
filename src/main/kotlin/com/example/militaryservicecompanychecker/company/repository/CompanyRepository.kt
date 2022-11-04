package com.example.militaryservicecompanychecker.company.repository

import com.example.militaryservicecompanychecker.company.entity.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, String> {
    @Query(value = "SELECT TOP 5 * FROM company WHERE (name REGEXP :regex)", nativeQuery = true)
    fun findTop5ByCompanyNameRegex(@Param("regex") companyName: String): List<Company>
}