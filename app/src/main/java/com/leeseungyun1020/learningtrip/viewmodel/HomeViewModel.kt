package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.leeseungyun1020.learningtrip.data.HomeRepository
import com.leeseungyun1020.learningtrip.model.Banner
import com.leeseungyun1020.learningtrip.model.Keyword
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.model.SimplePlace

class HomeViewModel(private val repository: HomeRepository = HomeRepository()) : ViewModel() {
    init {
        repository.loadHomeContents()
    }

    val recommendedKeywords: LiveData<List<Keyword>> = repository.recommendedKeywords
    val mainBanners: LiveData<List<Banner>> = repository.mainBanners
    val recommendedPlaces: LiveData<List<SimplePlace>> = repository.recommendedPlaces
    val recommendedCourses: LiveData<List<SimpleCourse>> = repository.recommendedCourses
}