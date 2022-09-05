package com.leeseungyun1020.learningtrip.model

import androidx.room.Entity
import androidx.room.PrimaryKey

class DetailedCourse(val id: Int, val name: String, val placeList: List<SimplePlace>)

fun DetailedCourse.toCourse(): Course {
    val course = Course(name = name)
    course.id = id
    return course
}

@Entity
data class Course(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}