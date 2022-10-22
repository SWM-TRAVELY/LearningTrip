package com.leeseungyun1020.learningtrip.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.auth.*
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.leeseungyun1020.learningtrip.network.loadAuthRequiredNetworkData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(private val context: Context) {
    private val _isSignIn = MutableLiveData<Boolean>(loadToken() != null)
    private val AUTH_PREF = "auth"
    val isSignIn: LiveData<Boolean>
        get() = _isSignIn

    val signUpError = MutableLiveData<Boolean>(false)
    val signInError = MutableLiveData<Boolean>(false)
    val user = MutableLiveData<User>()

    fun loadToken(): String? {
        val pref = context.getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE)
        return pref.getString("token", null)
    }

    fun loadRefreshToken(): String? {
        val pref = context.getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE)
        return pref.getString("refreshToken", null)
    }

    fun saveToken(token: String) {
        val pref = context.getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE)
        pref.edit().putString("token", token).apply()
        _isSignIn.value = true
    }

    fun saveInitialToken(refreshToken: String, token: String) {
        val pref = context.getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE)
        pref.edit().putString("refreshToken", refreshToken).putString("token", token).apply()
        _isSignIn.value = true
    }

    fun deleteToken() {
        val pref = context.getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE)
        pref.edit().remove("token").remove("refreshToken").apply()
        _isSignIn.value = false
    }

    fun signIn(email: String, password: String) {
        RetrofitClient.authService.signIn(SignInRequest(email, password)).enqueue(
            object : Callback<AuthResponse<TokenResponse>> {
                override fun onFailure(call: Call<AuthResponse<TokenResponse>>, t: Throwable) {
                    signInError.value = true
                    Log.d(TAG, "onFailure: $t")
                }

                override fun onResponse(
                    call: Call<AuthResponse<TokenResponse>>,
                    response: Response<AuthResponse<TokenResponse>>
                ) {
                    val body = response.body()
                    Log.d(TAG, "onResponse: $response ${response.body()}")
                    if (response.isSuccessful && response.code() == 200 && body != null) {
                        when (body.status) {
                            200 -> {
                                signInError.value = false
                                saveInitialToken(body.data.refreshToken, body.data.accessToken)
                            }
                            else -> {
                                signInError.value = true
                            }
                        }
                    } else {
                        signInError.value = true
                    }
                }
            }
        )
    }

    fun signUp(email: String, password: String, nickname: String, phone: String) {
        RetrofitClient.authService.signUp(SignUpRequest(email, password, nickname, phone)).enqueue(
            object : Callback<AuthResponse<TokenResponse>> {
                override fun onFailure(call: Call<AuthResponse<TokenResponse>>, t: Throwable) {
                    signUpError.value = true
                    Log.d(TAG, "onFailure: $t")
                }

                override fun onResponse(
                    call: Call<AuthResponse<TokenResponse>>,
                    response: Response<AuthResponse<TokenResponse>>
                ) {
                    val body = response.body()
                    Log.d(TAG, "onResponse: $response ${response.body()}")
                    if (response.isSuccessful && response.code() == 200 && body != null) {
                        when (body.status) {
                            200 -> {
                                signUpError.value = false
                                saveInitialToken(body.data.refreshToken, body.data.accessToken)
                            }
                            else -> {
                                signUpError.value = true
                            }
                        }
                    } else {
                        signUpError.value = true
                    }
                }
            }
        )
    }

    fun autoSignIn() {
        val refreshToken = loadRefreshToken()
        if (refreshToken != null) {
            RetrofitClient.authService.autoSignIn(AutoSignInRequest(refreshToken)).enqueue(
                object : Callback<AuthResponse<TokenResponse>> {
                    override fun onFailure(call: Call<AuthResponse<TokenResponse>>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                        _isSignIn.value = false
                    }

                    override fun onResponse(
                        call: Call<AuthResponse<TokenResponse>>,
                        response: Response<AuthResponse<TokenResponse>>
                    ) {
                        val body = response.body()
                        Log.d(TAG, "onResponse: $response ${response.body()}")
                        if (response.isSuccessful && response.code() == 200 && body != null) {
                            when (body.status) {
                                200 -> {
                                    Log.d(TAG, "AUTO SIGN IN SUCCESS")
                                    saveInitialToken(body.data.refreshToken, body.data.accessToken)
                                    _isSignIn.value = true
                                }
                                else -> {
                                    deleteToken()
                                    _isSignIn.value = false
                                }
                            }
                        } else {
                            deleteToken()
                            _isSignIn.value = false
                        }
                    }
                }
            )
        }
    }

    fun reloadToken() {
        val refreshToken = loadRefreshToken()
        if (refreshToken != null) {
            RetrofitClient.authService.reloadToken(ReloadTokenRequest(refreshToken)).enqueue(
                object : Callback<AuthResponse<ReloadTokenResponse>> {
                    override fun onFailure(
                        call: Call<AuthResponse<ReloadTokenResponse>>,
                        t: Throwable
                    ) {
                        Log.d(TAG, "onFailure: $t")
                    }

                    override fun onResponse(
                        call: Call<AuthResponse<ReloadTokenResponse>>,
                        response: Response<AuthResponse<ReloadTokenResponse>>
                    ) {
                        val body = response.body()
                        Log.d(TAG, "onResponse: $response ${response.body()}")
                        if (response.isSuccessful && response.code() == 200 && body != null) {
                            when (body.status) {
                                200 -> {
                                    Log.d(TAG, "REFRESH TOKEN SUCCESS")
                                    saveToken(body.data.accessToken)
                                }
                                else -> {
                                    deleteToken()
                                }
                            }
                        } else {
                            deleteToken()
                        }
                    }
                }
            )
        }
    }

    fun loadUserInfo() {
        loadToken()?.let {
            RetrofitClient.authService.getUserInfo(it).loadAuthRequiredNetworkData(user, null) {
                reloadToken()
            }
        }
    }
}