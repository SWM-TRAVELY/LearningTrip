package com.leeseungyun1020.learningtrip.model.auth

data class UserInfoResponse(
    val email: String,
    val nickname: String,
    val phone: String,
    val image: String,
    val provider: String
)
