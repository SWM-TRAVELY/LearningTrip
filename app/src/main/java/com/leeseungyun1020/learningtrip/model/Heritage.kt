package com.leeseungyun1020.learningtrip.model

import com.google.gson.annotations.SerializedName

data class Heritage(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageURL")
    val imageURL: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("category")
    val category: String,
)

data class SimpleHeritage(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageURL")
    val imageURL: String?,
)

fun Heritage.toSimpleHeritage(): SimpleHeritage {
    return SimpleHeritage(
        id = id,
        name = name,
        imageURL = imageURL,
    )
}