package com.leeseungyun1020.learningtrip.model.course

import com.google.gson.annotations.SerializedName

data class CourseRequestOption(
    @SerializedName("start")
    val start: Long,
    @SerializedName("end")
    val end: Long,
    @SerializedName("location")
    val location: String,
    @SerializedName("locationOption")
    val locationOption: String,
    @SerializedName("grade")
    val grade: String,
    @SerializedName("gradeOption")
    val gradeOption: String,
    @SerializedName("keyword")
    val keyword: List<String>,
)
