package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.data.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository = HomeRepository()) : ViewModel() {
    init {
        viewModelScope.launch {
            repository.loadHomeContents()
        }
    }

    val recommendedKeywords = repository.recommendedKeywords
    val mainBanners = repository.mainBanners
    val recommendedPlaces = repository.recommendedPlaces
    val recommendedCourses = repository.recommendedCourses
}