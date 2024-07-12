package com.example.militaryservicecompanychecker.company.converter

import com.example.militaryservicecompanychecker.company.enums.ServiceType
import jakarta.persistence.AttributeConverter

class ServiceTypeConverter : AttributeConverter<ServiceType, String> {
    override fun convertToDatabaseColumn(attribute: ServiceType?): String {
        return attribute.toString()
    }

    override fun convertToEntityAttribute(dbData: String?): ServiceType {
        return ServiceType[dbData.toString()]!!
    }
}