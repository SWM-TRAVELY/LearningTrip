package com.leeseungyun1020.learningtrip.model.course

import com.google.gson.annotations.SerializedName

data class GroupItem(
    @SerializedName("group")
    val name: String,
    @SerializedName("options")
    val options: List<String>
)
