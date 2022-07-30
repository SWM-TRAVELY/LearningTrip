package com.leeseungyun1020.learningtrip.model

import com.leeseungyun1020.learningntripapitest.SimpleHeritage

data class Place(
    val id: String,
    val name: String,
    val typeId: String,
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
    val heritageList: List<SimpleHeritage>,
)

data class SimplePlace(val id: String, val name: String, val typeId: String, val imageURL: String)

fun Place.toSimplePlace(): SimplePlace {
    return SimplePlace(id, name, typeId, imageURL)
}