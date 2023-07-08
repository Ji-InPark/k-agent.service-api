package com.example.militaryservicecompanychecker.company.controller

import com.example.militaryservicecompanychecker.company.controller.dto.CompanyAutoCompleteRequest
import com.example.militaryservicecompanychecker.company.controller.dto.CompanyAutoCompleteResponse
import com.example.militaryservicecompanychecker.company.controller.dto.CompanyRequest
import com.example.militaryservicecompanychecker.company.controller.dto.CompanyResponse
import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector
import com.example.militaryservicecompanychecker.company.enums.ServiceType
import com.example.militaryservicecompanychecker.company.service.CompanyService
import com.example.militaryservicecompanychecker.company.util.Util.safeValueOf
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@CrossOrigin(origins = ["https://k-agent.services/", "http://localhost:3000/"])
class CompanyController(
    private val companyService: CompanyService
) {
    @PostMapping("/search/autocomplete")
    fun searchCompanyByRegex(@RequestBody request: CompanyAutoCompleteRequest): CompanyAutoCompleteResponse {
        return CompanyAutoCompleteResponse(
            companyService.searchCompanyByRegex(request.regex).map { it.companyName }
        )
    }

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

    @GetMapping("/kreditjob/{id}")
    fun getKreditJobKey(@PathVariable("id") id: Long): String {
        return companyService.getKreditJobKeyAndUpdateToCompany(id)
    }

    @PostMapping(
        "/company", consumes = [
            MediaType.APPLICATION_OCTET_STREAM_VALUE,
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
        ]
    )
    fun updateCompany(
        @RequestPart("serviceType") serviceType: String,
        @RequestPart("file") file: MultipartFile
    ): CompanyResponse {
        return CompanyResponse(
            companyService.deleteAndCreateCompanyByFile(file, ServiceType[serviceType]!!)
        )
    }
}