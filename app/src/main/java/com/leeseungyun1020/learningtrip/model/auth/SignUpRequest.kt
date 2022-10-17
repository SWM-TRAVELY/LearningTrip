package com.leeseungyun1020.learningtrip.model.auth

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("username")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("phone")
    val phone: String
)
