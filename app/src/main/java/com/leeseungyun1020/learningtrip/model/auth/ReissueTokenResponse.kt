package com.leeseungyun1020.learningtrip.model.auth

import com.google.gson.annotations.SerializedName

data class ReissueTokenResponse(
    @SerializedName("access_token")
    val accessToken: String
)