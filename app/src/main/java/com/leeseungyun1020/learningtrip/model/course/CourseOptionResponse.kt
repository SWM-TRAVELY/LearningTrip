package com.leeseungyun1020.learningtrip.model.course

import com.google.gson.annotations.SerializedName

data class CourseOptionResponse(
    @SerializedName("province")
    val locations: List<GroupItem>,
    @SerializedName("grade")
    val grades: List<GroupItem>,
    @SerializedName("keyword")
    val keywords: List<String>
)
