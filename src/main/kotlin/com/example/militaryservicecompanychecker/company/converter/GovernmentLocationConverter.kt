package com.example.militaryservicecompanychecker.company.converter

import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import jakarta.persistence.AttributeConverter

class GovernmentLocationConverter : AttributeConverter<GovernmentLocation, String> {
    override fun convertToDatabaseColumn(attribute: GovernmentLocation?): String {
        return attribute.toString()
    }

    override fun convertToEntityAttribute(dbData: String?): GovernmentLocation {
        return GovernmentLocation[dbData.toString()]!!
    }
}