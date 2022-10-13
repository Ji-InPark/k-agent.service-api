package com.example.militaryservicecompanychecker.company.controller

import com.example.militaryservicecompanychecker.company.controller.dto.CompanyRequest
import com.example.militaryservicecompanychecker.company.controller.dto.CompanyResponse
import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector
import com.example.militaryservicecompanychecker.company.service.CompanyService
import com.example.militaryservicecompanychecker.company.util.Util.safeValueOf
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["https://k-agent.services/", "http://localhost:3000/"])
class CompanyController(
    @Autowired
    private val companyService: CompanyService
) {
    @PostMapping("/search")
    fun searchCompany(@RequestBody request: CompanyRequest): CompanyResponse {
        return CompanyResponse(
            companyService.searchCompany(
                request.companyName.toString(),
                safeValueOf<GovernmentLocation>(request.governmentLocation.toString()),
                safeValueOf<Sector>(request.sector.toString())
            )
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
}