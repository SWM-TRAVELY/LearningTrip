package com.leeseungyun1020.learningtrip.model.auth

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("access_token")
    val accessToken: String
)
