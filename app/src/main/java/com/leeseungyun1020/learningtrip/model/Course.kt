package com.leeseungyun1020.learningtrip.model

import com.google.gson.annotations.SerializedName

class Course(
    val id: Int,
    val name: String,
    val placeList: List<SimplePlace>?,
)

data class SimpleCourse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageURL")
    val imageURL: String?,
    @SerializedName("place1")
    val place1: String?,
    @SerializedName("place2")
    val place2: String?,
    @SerializedName("place3")
    val place3: String?,
)