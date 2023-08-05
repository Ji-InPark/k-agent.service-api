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
    @Query(value = "SELECT * FROM company WHERE name ~ :regex LIMIT 5", nativeQuery = true)
    fun findTop5ByCompanyNameRegex(@Param("regex") companyName: String): List<Company>

    @Query(
        value = "SELECT * FROM company WHERE name ~ :name " +
                "AND (:sector is null or sector = :sector) " +
                "AND (:governmentLocation is null or government_location = :governmentLocation)" +
                "ORDER BY id ASC",
        nativeQuery = true
    )
    fun findAllByGovernmentLocationOrCompanySectorAndCompanyName(
        @Param("name") companyName: String,
        @Param("governmentLocation") governmentLocation: String?,
        @Param("sector") companySector: String?
    ): List<Company>

    fun findAllByCompanyNameContainsOrderByIdAsc(companyName: String): List<Company>

    fun findById(id: Long): Optional<Company>

    fun deleteByServiceType(serviceType: ServiceType)
}