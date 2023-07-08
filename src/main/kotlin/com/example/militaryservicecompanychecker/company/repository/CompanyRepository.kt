package com.example.militaryservicecompanychecker.company.repository

import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.enums.ServiceType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CompanyRepository : JpaRepository<Company, String> {
    @Query(value = "SELECT TOP 5 * FROM company WHERE (name REGEXP :regex)", nativeQuery = true)
    fun findTop5ByCompanyNameRegex(@Param("regex") companyName: String): List<Company>

    @Query(
        value = "SELECT * FROM company WHERE name like %:name% OR (sector = :sector AND government_location = :governmentLocation)",
        nativeQuery = true
    )
    fun findAllByGovernmentLocationOrCompanySectorAndCompanyName(
        @Param("name") companyName: String,
        @Param("governmentLocation") governmentLocation: String?,
        @Param("sector") companySector: String?
    ): List<Company>

    fun findById(id: Long): Optional<Company>

    fun deleteByServiceType(serviceType: ServiceType)
}