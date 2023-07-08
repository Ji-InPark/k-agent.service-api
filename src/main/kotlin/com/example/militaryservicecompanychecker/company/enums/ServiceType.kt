package com.example.militaryservicecompanychecker.company.enums

enum class ServiceType(val serviceType: String) {
    산업기능요원("산업기능요원"),
    전문연구요원("전문연구요원"),
    승선근무예비역("승선근무예비역");

    companion object {
        private val map = ServiceType.values().associateBy { it.serviceType }
        operator fun get(value: String) = map[value]
    }
}