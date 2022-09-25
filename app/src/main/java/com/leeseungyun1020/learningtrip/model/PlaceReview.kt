package com.leeseungyun1020.learningtrip.model

data class PlaceReview(
    val placeId: Int,
    val rating: Double,
    val review: Int,
)

data class SimplePlaceReview(
    val placeId: Int,
    val placeImageURL: String,
    val placeName: String,
    val rating: Double,
    val reviewImageURL: String,
    val content: String
)