package com.example.militaryservicecompanychecker.company.util

object Util {
    inline fun <reified T : Enum<T>> safeValueOf(type: String?): T? {
        return try {
            java.lang.Enum.valueOf(T::class.java, type)
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    fun String.toCompanyKeyword(): String {
        return this.replace("(주)", "")
            .replace("(유)", "")
            .replace("주식회사", "")
    }
}