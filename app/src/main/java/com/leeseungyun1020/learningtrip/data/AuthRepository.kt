package com.leeseungyun1020.learningtrip.data

import android.content.Context

class AuthRepository(private val context: Context) {
    fun loadToken(): String? {
        val pref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        return pref.getString("token", null)
    }

    fun loadRefreshToken(): String? {
        val pref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        return pref.getString("refreshToken", null)
    }

    fun saveToken(token: String) {
        val pref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        pref.edit().putString("token", token).apply()
    }

    fun saveInitialToken(refreshToken: String, token: String) {
        val pref = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        pref.edit().putString("refreshToken", refreshToken).putString("token", token).apply()
    }
}