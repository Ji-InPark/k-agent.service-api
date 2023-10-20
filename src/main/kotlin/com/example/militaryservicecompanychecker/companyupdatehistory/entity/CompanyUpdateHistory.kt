package com.example.militaryservicecompanychecker.companyupdatehistory.entity

import com.example.militaryservicecompanychecker.common.entity.BaseEntity
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "company_update_history", schema = "PUBLIC")
class CompanyUpdateHistory(
    var history: LocalDate
) : BaseEntity()