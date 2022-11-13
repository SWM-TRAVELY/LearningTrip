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

private const val AUTH_PREF = "auth"

class AuthRepository(private val context: Context) {
    private val _isSignIn = MutableLiveData<Boolean>(false)
    private val _token = MutableLiveData<String?>()

    val isSignIn: LiveData<Boolean>
        get() = _isSignIn
    val token: LiveData<String?>
        get() = _token
    private val pref by lazy {
        context.getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE)
    }

    private val _signUpError = MutableLiveData<Boolean>(false)
    private val _signInError = MutableLiveData<Boolean>(false)
    private val _user = MutableLiveData<User>()

    val signUpError: LiveData<Boolean>
        get() = _signUpError
    val signInError: LiveData<Boolean>
        get() = _signInError
    val user: LiveData<User>
        get() = _user

    private fun loadToken(): String? {
        val token = pref.getString("token", null)
        Log.d(TAG, "loadToken: $token")
        _token.value = token
        return token
    }

    private fun loadRefreshToken(): String? {
        return pref.getString("refreshToken", null)
    }

    fun saveToken(token: String) {
        pref.edit().putString("token", token).apply()
        _token.value = token
        _isSignIn.value = true
    }

    fun saveInitialToken(refreshToken: String, token: String) {
        Log.d(TAG, "saveInitialToken: $refreshToken, $token")
        pref.edit().putString("refreshToken", refreshToken).putString("token", token).apply()
        _token.value = token
        _isSignIn.value = true
    }

    fun deleteToken() {
        pref.edit().remove("token").remove("refreshToken").apply()
        Log.d(TAG, "deleteToken: ${pref.getString("token", null)}")
        _token.value = null
        _isSignIn.value = false
    }

    fun signIn(email: String, password: String) {
        RetrofitClient.authService.signIn(SignInRequest(email, password)).enqueue(
            object : Callback<AuthResponse<TokenResponse>> {
                override fun onFailure(call: Call<AuthResponse<TokenResponse>>, t: Throwable) {
                    _signInError.value = true
                    Log.d(TAG, "onFailure: $t")
                }

                override fun onResponse(
                    call: Call<AuthResponse<TokenResponse>>,
                    response: Response<AuthResponse<TokenResponse>>
                ) {
                    val body = response.body()
                    Log.d(TAG, "signin-onResponse: $response ${response.body()}")
                    if (response.isSuccessful && response.code() == 200 && body != null) {
                        when (body.status) {
                            200 -> {
                                _signInError.value = false
                                saveInitialToken(body.data.refreshToken, body.data.accessToken)
                                loadUserInfo()
                            }
                            else -> {
                                _signInError.value = true
                            }
                        }
                    } else {
                        _signInError.value = true
                    }
                }
            }
        )
    }

    fun signUp(email: String, password: String, nickname: String, phone: String) {
        RetrofitClient.authService.signUp(SignUpRequest(email, password, nickname, phone)).enqueue(
            object : Callback<AuthResponse<TokenResponse>> {
                override fun onFailure(call: Call<AuthResponse<TokenResponse>>, t: Throwable) {
                    _signUpError.value = true
                    Log.d(TAG, "signup-onFailure: $t")
                }

                override fun onResponse(
                    call: Call<AuthResponse<TokenResponse>>,
                    response: Response<AuthResponse<TokenResponse>>
                ) {
                    val body = response.body()
                    Log.d(TAG, "signup-onResponse: $response ${response.body()}")
                    if (response.isSuccessful && response.code() == 200 && body != null) {
                        when (body.status) {
                            200 -> {
                                Log.d(TAG, "signup: 200")
                                _signUpError.value = false
                                saveInitialToken(body.data.refreshToken, body.data.accessToken)
                                loadUserInfo()
                            }
                            else -> {
                                _signUpError.value = true
                            }
                        }
                    } else {
                        _signUpError.value = true
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
                        Log.d(TAG, "auto-onFailure: $t")
                        _isSignIn.value = false
                    }

                    override fun onResponse(
                        call: Call<AuthResponse<TokenResponse>>,
                        response: Response<AuthResponse<TokenResponse>>
                    ) {
                        val body = response.body()
                        Log.d(TAG, "auto-onResponse: $response ${response.body()}")
                        if (response.isSuccessful && response.code() == 200 && body != null) {
                            when (body.status) {
                                200 -> {
                                    Log.d(TAG, "AUTO SIGN IN SUCCESS")
                                    saveInitialToken(body.data.refreshToken, body.data.accessToken)
                                    loadUserInfo()
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
                        Log.d(TAG, "reload-onFailure: $t")
                    }

                    override fun onResponse(
                        call: Call<AuthResponse<ReloadTokenResponse>>,
                        response: Response<AuthResponse<ReloadTokenResponse>>
                    ) {
                        val body = response.body()
                        Log.d(TAG, "reload-onResponse: $response ${response.body()}")
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
            RetrofitClient.authService.getUserInfo(it).loadAuthRequiredNetworkData(_user, null) {
                reloadToken()
            }
        }
    }

    fun updateUserInfo(nickname: String, phone: String) {
        loadToken()?.let {
            RetrofitClient.authService.updateUserInfo(
                UpdateUserInfoRequest(
                    listOf(
                        "nickname",
                        "phone"
                    ), listOf(nickname, phone)
                ), it
            ).loadAuthRequiredNetworkData(_user, null) {
                reloadToken()
            }
        }
    }

    fun refreshSignInError() {
        _signInError.value = false
    }

    fun refreshSignUpError() {
        _signUpError.value = false
    }
}