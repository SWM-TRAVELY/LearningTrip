package com.leeseungyun1020.learningtrip.model.auth

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String?,
    @SerializedName("exp")
    val exp: Int?,
    @SerializedName("image")
    val imageURL: String?,
    @SerializedName("level")
    val level: String?,
    @SerializedName("loginProvider")
    val authProvider: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("phone")
    val phone: String?,
)