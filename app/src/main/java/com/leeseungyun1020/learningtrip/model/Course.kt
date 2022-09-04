package com.leeseungyun1020.learningtrip.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

class DetailedCourse(val id: Int, val name: String, val placeList: List<SimplePlace>)

fun DetailedCourse.toCourse(): Course {
    val course = Course(name = name, placeIdList = placeList.map { it.id })
    course.id = id
    return course
}

@Entity
data class Course(
    val name: String,
    val placeIdList: List<Int>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class PlaceIdListConverter {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        return value.split(",").map { it.toInt() }
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        return list.joinToString(",")
    }
}