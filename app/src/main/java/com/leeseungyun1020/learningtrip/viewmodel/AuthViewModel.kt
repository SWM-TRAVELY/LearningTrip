package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leeseungyun1020.learningtrip.data.AuthRepository

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun isSingIn() = authRepository.loadToken() != null

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