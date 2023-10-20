package com.example.militaryservicecompanychecker.byis.service

import com.example.militaryservicecompanychecker.common.service.ApiService
import com.example.militaryservicecompanychecker.common.util.EnumUtil.toGovernmentLocation
import com.example.militaryservicecompanychecker.common.util.EnumUtil.toSector
import com.example.militaryservicecompanychecker.common.util.Util.toCompanyKeyword
import com.example.militaryservicecompanychecker.company.entity.Company
import com.example.militaryservicecompanychecker.company.enums.ServiceType
import okhttp3.FormBody
import okhttp3.Response
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream

@Service
class ByisService(
    private val apiService: ApiService
) {
    fun extractCompanies(): MutableList<Company> {
        val serviceTypeMap = mapOf(
            1 to ServiceType.산업기능요원,
            2 to ServiceType.전문연구요원,
            3 to ServiceType.승선근무예비역,
        )

        val companies = mutableListOf<Company>()

        for ((key, serviceType) in serviceTypeMap) {
            val response = requestCompanyInfos(key)
            val workbook = convertToWorkbook(response)
            companies.addAll(createCompanies(workbook, serviceType))
        }

        return companies
    }

    private fun createCompanies(
        workbook: HSSFWorkbook,
        serviceType: ServiceType
    ): MutableList<Company> {
        val sheet = workbook.getSheetAt(0)
        val headerMap =
            sheet.getRow(0).toList().withIndex().associate { it.value.toString() to it.index }

        val companies = mutableListOf<Company>()
        for (i in 1.until(sheet.physicalNumberOfRows)) {
            val row = sheet.getRow(i)
            val companyName = row.getCell(headerMap["업체명"]!!).stringCellValue
            val newCompany = Company(
                companyName = companyName,
                governmentLocation = row.getCell(headerMap["지방청"]!!).stringCellValue.toGovernmentLocation(),
                companyLocation = row.getCell(headerMap["주소"]!!).stringCellValue,
                companyPhoneNumber = row.getCell(headerMap["전화번호"]!!).stringCellValue,
                companyFaxNumber = row.getCell(headerMap["팩스번호"]!!).stringCellValue,
                companySector = row.getCell(headerMap["업종"]!!).stringCellValue.toSector(),
                companyScale = row.getCell(headerMap["기업규모"]!!).stringCellValue,
                serviceType = serviceType,
                companyKeyword = companyName.toCompanyKeyword()
            )
            companies.add(newCompany)
        }

        return companies
    }

    private fun convertToWorkbook(response: Response): HSSFWorkbook {
        val stream = ByteArrayInputStream(response.body?.bytes())
        return HSSFWorkbook(stream)
    }

    private fun requestCompanyInfos(serviceTypeNumber: Int): Response {
        val url = "https://work.mma.go.kr/caisBYIS/search/downloadBYJJEopCheExcel.do"
        val body = FormBody.Builder()
            .add("eopjong_gbcd", serviceTypeNumber.toString())
            .build()

        return apiService.post(url, body)
    }
}