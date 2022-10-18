package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leeseungyun1020.learningtrip.data.AuthRepository

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    val isSignIn = authRepository.isSignIn
    val signInError = authRepository.signInError
    val signUpError = authRepository.signUpError
    val token = authRepository.loadToken()

    fun signInWithTokens(refreshToken: String, token: String) {
        authRepository.saveInitialToken(refreshToken, token)
    }

    fun signIn(email: String, password: String) {
        authRepository.signIn(email, password)
    }

    fun signUp(email: String, password: String, nickname: String, phone: String) {
        authRepository.signUp(email, password, nickname, phone)
    }

    fun signOut() {
        authRepository.deleteToken()
    }

    fun autoSignIn() {
        authRepository.autoSignIn()
    }

    fun reloadToken() {
        authRepository.reloadToken()
    }

}

class AuthViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}