package com.example.militaryservicecompanychecker.company.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "company", schema = "PUBLIC")
class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    val id: Long,

    @Column(name = "name")
    val companyName: String?,

    @Column(name = "government_location")
    val governmentLocation: String?,

    @Column(name = "location")
    val companyLocation: String?,

    @Column(name = "phone_number")
    val companyPhoneNumber: String?,

    @Column(name = "fax_number")
    val companyFaxNumber: String?,

    @Column(name = "sector")
    val companySector: String?,

    @Column(name = "scale")
    val companyScale: String?,

    @Column(name = "service_type")
    val serviceType: String?,

    @Column(name = "keyword")
    val companyKeyword: String?,

    var kreditJobKey: String?,
)