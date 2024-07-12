package com.example.militaryservicecompanychecker.company.repository

import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.entity.QCompany
import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class CustomCompanyRepository(
    private val queryFactory: JPAQueryFactory
) {
    fun findAllByCondition(
        companyName: String,
        sector: Sector?,
        governmentLocation: GovernmentLocation?,
        pageRequest: PageRequest
    ): MutableList<Company> {
        val query = getFindAllByConditionQuery(companyName, sector, governmentLocation)

        return query.offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetch()
    }

    fun countAllByCondition(
        companyName: String,
        sector: Sector?,
        governmentLocation: GovernmentLocation?
    ): Long {
        val query = getFindAllByConditionQuery(companyName, sector, governmentLocation)

        return query.fetch().size.toLong()
    }

    private fun getFindAllByConditionQuery(
        companyName: String,
        sector: Sector?,
        governmentLocation: GovernmentLocation?
    ): JPAQuery<Company> {
        val company = QCompany.company
        val query = queryFactory.selectFrom(company)

        query.where(company.companyName.contains(companyName))
        if (sector != null) query.where(company.companySector.eq(sector))
        if (governmentLocation != null) query.where(company.governmentLocation.eq(governmentLocation))

        return query
    }
}