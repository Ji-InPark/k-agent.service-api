package com.example.militaryservicecompanychecker.company.entity

import com.example.militaryservicecompanychecker.company.converter.GovernmentLocationConverter
import com.example.militaryservicecompanychecker.company.converter.SectorConverter
import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "company", schema = "PUBLIC", indexes = [Index(columnList = "name")])
class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name")
    val companyName: String,

    @Column(name = "government_location")
    @Convert(converter = GovernmentLocationConverter::class)
    val governmentLocation: GovernmentLocation,

    @Column(name = "location")
    val companyLocation: String,

    @Column(name = "phone_number")
    @JsonIgnore
    val companyPhoneNumber: String,

    @Column(name = "fax_number")
    @JsonIgnore
    val companyFaxNumber: String,

    @Column(name = "sector")
    @Convert(converter = SectorConverter::class)
    val companySector: Sector,

    @Column(name = "scale")
    val companyScale: String,

    @Column(name = "service_type")
    val serviceType: String,

    @Column(name = "keyword")
    val companyKeyword: String,

    @Column(name = "kreditjob_key")
    var kreditJobKey: String,
)