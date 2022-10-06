package com.example.militaryservicecompanychecker.company.util

object Util {
    fun Int.convertToServiceType(): String {
        return when (this) {
            1 -> "산업기능요원"
            2 -> "전문연구요원"
            else -> "승선근무예비역"
        }
    }

    inline fun <reified T : Enum<T>> safeValueOf(type: String?): T? {
        return try {
            java.lang.Enum.valueOf(T::class.java, type)
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}