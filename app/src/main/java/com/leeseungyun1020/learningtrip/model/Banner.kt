package com.leeseungyun1020.learningtrip.model

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageURL")
    val imageURL: String
)
