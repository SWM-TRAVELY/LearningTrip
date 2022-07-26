package com.leeseungyun1020.learningtrip.model

import com.google.gson.annotations.SerializedName

data class Keyword(
    @SerializedName("name")
    val name: String,
    @SerializedName("imageURL")
    val imageURL: String
)