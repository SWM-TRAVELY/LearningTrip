package com.leeseungyun1020.learningtrip.model

import androidx.room.Entity
import androidx.room.PrimaryKey

class Course(
    val id: Int,
    val name: String,
    val placeList: List<SimplePlace>?,
)

data class SimpleCourse(
    val id: Int,
    val name: String,
    val imageURL: String?,
    val place1: String?,
    val place2: String?,
    val place3: String?,
)