package com.leeseungyun1020.learningtrip.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Place(
    @SerializedName("id")
    @PrimaryKey val id: Int,
    @SerializedName("name")
    val name: String?,
    val typeId: Int?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("textbook")
    val chkInTextbook: Boolean?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("tel")
    val tel: String?,
    @SerializedName("description")
    val overview: String?,
    @SerializedName("imageURL")
    val imageURL: String?,
    @SerializedName("restDate")
    val restDate: String?,
    @SerializedName("useTime")
    val useTime: String?,
    @SerializedName("parking")
    val chkParking: Boolean?,
    @SerializedName("babyCarriage")
    val chkBabyCarriage: Boolean?,
    @SerializedName("pet")
    val chkPets: Boolean?,
    @SerializedName("experienceAge")
    val ageAvailable: String?,
    @SerializedName("experienceInfo")
    val expGuide: String?,
    @SerializedName("worldCulturalHeritage")
    val chkWorldCultural: Boolean?,
    @SerializedName("worldNaturalHeritage")
    val chkWorldNatural: Boolean?,
    @SerializedName("worldRecordHeritage")
    val chkWorldRecord: Boolean?,
)

data class SimplePlace(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    val typeId: Int?,
    @SerializedName("province")
    val address: String?,
    @SerializedName("imageURL")
    val imageURL: String?,
    @SerializedName("description")
    val overview: String? = null,
) {
    constructor(id: String, name: String, typeId: String, address: String, imageURL: String) : this(
        id.toInt(),
        name,
        typeId.toInt(),
        address,
        imageURL
    )

    constructor(id: Int, name: String, typeId: String, address: String, imageURL: String) : this(
        id,
        name,
        typeId.toInt(),
        address,
        imageURL
    )

    constructor(id: String, name: String, typeId: Int, address: String, imageURL: String) : this(
        id.toInt(),
        name,
        typeId,
        address,
        imageURL
    )
}

data class SimpleCoursePlace(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("imageURL")
    val imageURL: String?,
    @SerializedName("description")
    val overview: String? = null,
    @SerializedName("day")
    val day: Int?,
    @SerializedName("sequence")
    val sequence: Int?,
    @SerializedName("distance")
    val distance: Double?,
    @SerializedName("time")
    val time: Int?,
)

fun Place.toSimplePlace(): SimplePlace {
    return SimplePlace(id, name, typeId, address, imageURL)
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