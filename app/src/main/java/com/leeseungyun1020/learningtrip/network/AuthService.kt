package com.leeseungyun1020.learningtrip.network

import com.leeseungyun1020.learningtrip.model.auth.AuthResponse
import com.leeseungyun1020.learningtrip.model.auth.SignUpRequest
import com.leeseungyun1020.learningtrip.model.auth.TokenResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Accept:application/json", "Content-Type: application/json")
    @POST("/user/signup")
    fun signUp(
        @Body signUpRequest: SignUpRequest
    ): retrofit2.Call<AuthResponse<TokenResponse>>

}