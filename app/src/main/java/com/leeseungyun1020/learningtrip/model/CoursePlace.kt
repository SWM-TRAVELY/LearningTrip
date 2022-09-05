package com.leeseungyun1020.learningtrip.model

import androidx.room.Entity

@Entity
data class CoursePlace(
    val courseId: Int,
    val placeId: Int,
    val index: Int
)