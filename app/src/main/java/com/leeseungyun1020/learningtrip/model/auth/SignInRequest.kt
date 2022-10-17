package com.leeseungyun1020.learningtrip.model.auth

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("username")
    val email: String,
    @SerializedName("password")
    val password: String
)
