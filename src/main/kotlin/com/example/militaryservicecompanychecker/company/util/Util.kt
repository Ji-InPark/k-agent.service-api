package com.example.militaryservicecompanychecker.company.util

object Util {
    fun Int.convertToServiceType(): String {
        return when (this) {
            1 -> "산업기능요원"
            2 -> "전문연구요원"
            else -> "승선근무예비역"
        }
    }
}