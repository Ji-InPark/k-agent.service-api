package com.example.militaryservicecompanychecker.companyupdatehistory.entity

import com.example.militaryservicecompanychecker.common.entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "company_update_history", schema = "PUBLIC")
class CompanyUpdateHistory(
    var history: LocalDate
) : BaseEntity()