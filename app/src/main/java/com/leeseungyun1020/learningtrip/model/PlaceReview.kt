package com.leeseungyun1020.learningtrip.model

data class PlaceReview(
    val placeId: Long,
    val rating: Double,
    val review: Int,
)

data class SimplePlaceReview(
    val placeId: Long,
    val placeImageURL: String,
    val placeName: String,
    val rating: Double,
    val reviewImageURL: String,
    val content: String
)