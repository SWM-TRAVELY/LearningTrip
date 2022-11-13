package com.leeseungyun1020.learningtrip.model.course

import com.google.gson.annotations.SerializedName

data class CourseResponse(
    @SerializedName("statusCodeValue")
    val code: Int,
    @SerializedName("statusCode")
    val message: String
)