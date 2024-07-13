package com.example.militaryservicecompanychecker.company.controller

import com.example.militaryservicecompanychecker.common.util.Util.safeValueOf
import com.example.militaryservicecompanychecker.company.controller.dto.CompanyAutoCompleteRequest
import com.example.militaryservicecompanychecker.company.controller.dto.CompanyRequest
import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector
import com.example.militaryservicecompanychecker.company.enums.ServiceType
import com.example.militaryservicecompanychecker.company.service.CompanyService
import org.springframework.data.domain.PageImpl
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["https://k-agent.services/", "http://localhost:3000/"])
class CompanyController(
    private val companyService: CompanyService,
    private val passwordEncoder: BCryptPasswordEncoder
) {
    @PostMapping("/search/autocomplete")
    fun searchCompanyByRegex(@RequestBody request: CompanyAutoCompleteRequest): List<String> {
        return companyService.searchCompanyByRegex(request.regex).map { it.companyName }
    }

    @PostMapping("/search")
    fun searchCompany(@RequestBody request: CompanyRequest): PageImpl<Company> {
        return companyService.searchCompany(
            request.companyName.toString(),
            safeValueOf<GovernmentLocation>(request.governmentLocation.toString()),
            safeValueOf<Sector>(request.sector.toString()),
            request.page,
            request.size
        )
    }

    @GetMapping("/government-locations")
    fun getGovernmentLocations(): Array<GovernmentLocation> {
        return companyService.getGovernmentLocations()
    }

    @GetMapping("/sectors")
    fun getSectors(): Array<Sector> {
        return companyService.getSectors()
    }

    @GetMapping("/serviceTypes")
    fun getServiceTypes(): Array<ServiceType> {
        return companyService.getServiceTypes()
    }

    @GetMapping("/wanted-insight/{id}")
    fun getWantedInsightKey(@PathVariable("id") id: Long): String? {
        return companyService.getOrRequestWantedInsightKey(id)
    }

    @PostMapping("/company")
    fun updateCompany(
        @RequestPart("password") password: String
    ): Any {
        if (!passwordEncoder.matches(
                password,
                System.getenv("ADMIN_PASSWORD")
            )
        ) return "비밀번호가 틀렸습니다."

        return companyService.updateCompanyInfoByBYIS()
    }
}