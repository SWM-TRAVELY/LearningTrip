package com.leeseungyun1020.learningtrip.model.auth

import com.google.gson.annotations.SerializedName

data class ReloadTokenRequest(
    @SerializedName("refresh_token")
    val refreshToken: String
)

data class ReloadTokenResponse(
    @SerializedName("access_token")
    val accessToken: String
)