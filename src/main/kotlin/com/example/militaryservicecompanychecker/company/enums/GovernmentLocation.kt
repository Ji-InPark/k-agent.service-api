package com.example.militaryservicecompanychecker.company.enums

enum class GovernmentLocation(val location: String) {
    강원("강원"),
    강원영동("강원영동"),
    경기북부("경기북부"),
    경남("경남"),
    경인("경인"),
    광주_전남("광주_전남"),
    대구_경북("대구_경북"),
    대전_충남("대전_충남"),
    병무("병무"),
    부산_울산("부산_울산"),
    서울("서울"),
    인천("인천"),
    전북("전북"),
    제주("제주"),
    충북("충북");

    companion object {
        private val map = GovernmentLocation.values().associateBy { it.location }
        operator fun get(value: String) = map[value]
    }
}