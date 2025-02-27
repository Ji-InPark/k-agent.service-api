package com.example.militaryservicecompanychecker.company.enums

enum class Sector(val sector: String) {
    게임SW("게임SW"),
    과기원("과기원"),
    과기원부설연구소("과기원부설연구소"),
    국가기관_등_연구소("국가기관_등_연구소"),
    국내건설("국내건설"),
    국외건설("국외건설"),
    기계("기계"),
    기능특기자("기능특기자"),
    기초연구연구기관("기초연구연구기관"),
    농기계수리("농기계수리"),
    농기계운전("농기계운전"),
    농산물가공("농산물가공"),
    대기업부설연구소("대기업부설연구소"),
    대학원연구기관("대학원연구기관"),
    동물약품("동물약품"),
    민영방산("민영방산"),
    방산연구기관("방산연구기관"),
    벤처기업부설연구소("벤처기업부설연구소"),
    생활용품("생활용품"),
    선광제련("선광제련"),
    섬유("섬유"),
    수산물가공("수산물가공"),
    수산업("수산업"),
    시멘요업("시멘요업"),
    식음료("식음료"),
    신발("신발"),
    애니메이션("애니메이션"),
    어민후계자("어민후계자"),
    에너지("에너지"),
    외항선박관리("외항선박관리"),
    외항화물("외항화물"),
    원양("원양"),
    의료의약("의료의약"),
    일반광물채굴("일반광물채굴"),
    임산물가공("임산물가공"),
    자연계대학부설연구기관("자연계대학부설연구기관"),
    전기("전기"),
    전자("전자"),
    정보처리("정보처리"),
    정부출연연구소("정부출연연구소"),
    중견기업부설연구소("중견기업부설연구소"),
    중소기업부설연구소("중소기업부설연구소"),
    지역혁신센터연구소("지역혁신센터연구소"),
    철강("철강"),
    통신기기("통신기기"),
    특정연구소("특정연구소"),
    해운업("해운업"),
    화학("화학"),
    후계농업민("후계농업민"),
    영상게임("영상게임");

    companion object {
        private val map = Sector.values().associateBy { it.sector }
        operator fun get(value: String) = map[value]
    }
}