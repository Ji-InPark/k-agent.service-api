package com.example.militaryservicecompanychecker.company.converter

import com.example.militaryservicecompanychecker.company.enums.Sector
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class SectorConverter : AttributeConverter<Sector, String> {
    override fun convertToDatabaseColumn(attribute: Sector?): String {
        return attribute.toString()
    }

    override fun convertToEntityAttribute(dbData: String?): Sector {
        return Sector[dbData.toString()]!!
    }
}