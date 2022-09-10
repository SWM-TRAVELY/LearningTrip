package com.leeseungyun1020.learningtrip.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private var _name by mutableStateOf("")
    val name: String
        get() = _name
    private var _email by mutableStateOf("")
    val email: String
        get() = _email
    private var _password by mutableStateOf("")
    val password: String
        get() = _password
    private var _passwordCheck by mutableStateOf("")
    val passwordCheck: String
        get() = _passwordCheck
    private var _nickname by mutableStateOf("")
    val nickname: String
        get() = _nickname
    private var _phone by mutableStateOf("")
    val phone: String
        get() = _phone

    private var _isPasswordChecked by mutableStateOf(true)
    val isPasswordChecked: Boolean
        get() = _isPasswordChecked

    private var _isNameError by mutableStateOf(false)
    val isNameError: Boolean
        get() = _isNameError
    private var _isEmailError by mutableStateOf(false)
    val isEmailError: Boolean
        get() = _isEmailError
    private var _isPasswordError by mutableStateOf(false)
    val isPasswordError: Boolean
        get() = _isPasswordError
    private var _isNicknameError by mutableStateOf(false)
    val isNicknameError: Boolean
        get() = _isNicknameError
    private var _isPhoneError by mutableStateOf(false)
    val isPhoneError: Boolean
        get() = _isPhoneError

    fun onUpdateName(name: String) {
        _name = name
    }

    fun onUpdateEmail(email: String) {
        _email = email
    }

    fun onUpdatePassword(password: String) {
        _password = password
    }

    fun onUpdatePasswordCheck(passwordCheck: String) {
        _isPasswordChecked = passwordCheck == password
        _passwordCheck = passwordCheck
    }

    fun onUpdateNickname(nickname: String) {
        _nickname = nickname
    }

    fun onUpdatePhone(phone: String) {
        _phone = phone
    }

    fun onSignUp() {
        _isPasswordChecked = passwordCheck == password

        _isNameError = name.length < 2
        _isEmailError = email.length < 5
        _isPasswordError = password.length < 8
        _isNicknameError = nickname.length < 2
        _isPhoneError = phone.length < 10

        if (!isNameError && !isEmailError && isPasswordChecked && !isPasswordError && !isNicknameError && !isPhoneError) {
            // TODO: 회원가입
        }
    }
}