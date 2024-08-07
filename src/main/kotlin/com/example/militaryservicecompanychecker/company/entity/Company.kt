package com.example.militaryservicecompanychecker.company.entity

import com.example.militaryservicecompanychecker.common.entity.BaseEntity
import com.example.militaryservicecompanychecker.company.converter.GovernmentLocationConverter
import com.example.militaryservicecompanychecker.company.converter.SectorConverter
import com.example.militaryservicecompanychecker.company.converter.ServiceTypeConverter
import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector
import com.example.militaryservicecompanychecker.company.enums.ServiceType
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "company", schema = "PUBLIC", indexes = [Index(columnList = "name")])
class Company(
    @Column(name = "name")
    var companyName: String,

    @Column(name = "government_location")
    @Convert(converter = GovernmentLocationConverter::class)
    var governmentLocation: GovernmentLocation,

    @Column(name = "location")
    var companyLocation: String,

    @Column(name = "phone_number")
    @JsonIgnore
    var companyPhoneNumber: String,

    @Column(name = "fax_number")
    @JsonIgnore
    var companyFaxNumber: String,

    @Column(name = "sector")
    @Convert(converter = SectorConverter::class)
    var companySector: Sector,

    @Column(name = "scale")
    var companyScale: String,

    @Column(name = "service_type")
    @Convert(converter = ServiceTypeConverter::class)
    var serviceType: ServiceType,

    @Column(name = "keyword")
    var companyKeyword: String,
) : BaseEntity() {
    @Column(name = "wanted_insight_key")
    var wantedInsightKey: String? = null
}