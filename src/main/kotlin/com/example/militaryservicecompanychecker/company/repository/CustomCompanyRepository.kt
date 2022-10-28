package com.example.militaryservicecompanychecker.company.repository

import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.entity.QCompany
import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class CustomCompanyRepository(
    val jpaQueryFactory: JPAQueryFactory
) {
    fun findCompaniesByNameAndSectorAndGovernmentLocation(
        companyName: String,
        governmentLocation: GovernmentLocation?,
        sector: Sector?
    ): List<Company> {
        val company = QCompany.company

        return jpaQueryFactory.select(company)
            .from(company)
            .where(
                company.companyName.contains(companyName),
                eqGovernmentLocation(governmentLocation),
                eqSector(sector)
            ).fetch()
    }

    private fun eqGovernmentLocation(governmentLocation: GovernmentLocation?): BooleanExpression? {
        val company = QCompany.company

        return if (governmentLocation == null) null
        else company.governmentLocation.eq(governmentLocation)
    }

    private fun eqSector(sector: Sector?): BooleanExpression? {
        val company = QCompany.company

        return if (sector == null) null
        else company.companySector.eq(sector)
    }
}