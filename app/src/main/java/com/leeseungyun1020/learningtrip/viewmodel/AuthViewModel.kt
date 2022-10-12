package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.ViewModel
import com.leeseungyun1020.learningtrip.data.AuthRepository

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun isSingIn() = authRepository.loadToken() != null

}