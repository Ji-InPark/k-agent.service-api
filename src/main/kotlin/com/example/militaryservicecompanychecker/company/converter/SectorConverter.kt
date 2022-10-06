package com.example.militaryservicecompanychecker.company.converter

import com.example.militaryservicecompanychecker.company.constants.Sector
import javax.persistence.AttributeConverter

class SectorConverter : AttributeConverter<Sector, String> {
    override fun convertToDatabaseColumn(attribute: Sector?): String {
        return attribute.toString()
    }

    override fun convertToEntityAttribute(dbData: String?): Sector {
        return Sector.valueOf(dbData.toString())
    }
}