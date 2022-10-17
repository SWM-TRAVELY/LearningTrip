package com.leeseungyun1020.learningtrip.model.auth

import com.google.gson.annotations.SerializedName

data class AutoSignInRequest(
    @SerializedName("refresh_token")
    val refreshToken: String
)
