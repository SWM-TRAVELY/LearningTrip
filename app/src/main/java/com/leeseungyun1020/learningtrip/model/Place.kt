package com.leeseungyun1020.learningtrip.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place(
    @PrimaryKey val id: Int,
    val name: String,
    val typeId: Int,
    val address: String,
    val chkInTextbook: Boolean,
    val latitude: Double,
    val longitude: Double,
    val tel: String,
    val overview: String,
    val imageURL: String,
    val restDate: String,
    val useTime: String,
    val chkParking: Boolean,
    val chkBabyCarriage: Boolean,
    val chkPets: Boolean,
    val ageAvailable: String,
    val expGuide: String,
    val chkWorldCultural: Boolean,
    val chkWorldNatural: Boolean,
    val chkWorldRecord: Boolean,
    //val heritageList: List<SimpleHeritage>,
)

data class SimplePlace(val id: Int, val name: String, val typeId: Int, val imageURL: String) {
    constructor(id: String, name: String, typeId: String, imageURL: String) : this(
        id.toInt(),
        name,
        typeId.toInt(),
        imageURL
    )

    constructor(id: Int, name: String, typeId: String, imageURL: String) : this(
        id,
        name,
        typeId.toInt(),
        imageURL
    )

    constructor(id: String, name: String, typeId: Int, imageURL: String) : this(
        id.toInt(),
        name,
        typeId,
        imageURL
    )
}

fun Place.toSimplePlace(): SimplePlace {
    return SimplePlace(id, name, typeId, imageURL)
}

/*

Place(
    id = 1,
    name = "서울역",
    typeId = 14,
    address = "서울시 중구 을지로동 서울역",
    chkInTextbook = true,
    latitude = 37.55,
    longitude = 126.98,
    tel = "051-000-0000",
    overview = "서울역은 서울시 중구 을지로동 서울역이다.",
    imageURL = "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34",
    restDate = "Rest Date",
    useTime = "Use Time",
    chkParking = false,
    chkBabyCarriage = false,
    chkPets = false,
    ageAvailable = "전연령",
    expGuide = "체험여행자료",
    chkWorldCultural = false,
    chkWorldNatural = false,
    chkWorldRecord = true,
)
 */