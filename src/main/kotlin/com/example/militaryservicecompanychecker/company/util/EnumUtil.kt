package com.example.militaryservicecompanychecker.company.util

import com.example.militaryservicecompanychecker.company.enums.GovernmentLocation
import com.example.militaryservicecompanychecker.company.enums.Sector

object EnumUtil {
    fun String.toGovernmentLocation(): GovernmentLocation {
        return GovernmentLocation[this.replace('.', '_')]!!
    }

    fun String.toSector(): Sector {
        return Sector[this.replace("/", "").replace(' ', '_')]!!
    }
}