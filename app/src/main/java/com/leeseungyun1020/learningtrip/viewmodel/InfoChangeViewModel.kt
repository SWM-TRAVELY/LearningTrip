package com.leeseungyun1020.learningtrip.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class InfoChangeViewModel : ViewModel() {
    private var _nickname by mutableStateOf("")
    val nickname: String
        get() = _nickname
    private var _phone by mutableStateOf("")
    val phone: String
        get() = _phone

    fun onUpdateNickname(nickname: String) {
        _nickname = nickname
    }

    fun onUpdatePhone(phone: String) {
        _phone = phone
    }

    fun check(): Boolean {
        if (nickname.isEmpty() || phone.isEmpty()) {
            return false
        }
        return true
    }
}