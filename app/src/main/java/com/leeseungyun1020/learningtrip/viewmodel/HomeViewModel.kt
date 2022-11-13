package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.ViewModel
import com.leeseungyun1020.learningtrip.data.HomeRepository

class HomeViewModel(private val repository: HomeRepository = HomeRepository()) : ViewModel() {
    init {
        repository.loadHomeContents()
    }

    val recommendedKeywords = repository.recommendedKeywords
    val mainBanners = repository.mainBanners
    val recommendedPlaces = repository.recommendedPlaces
    val recommendedCourses = repository.recommendedCourses
}