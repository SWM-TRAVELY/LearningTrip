package com.leeseungyun1020.learningtrip.data

import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.Banner
import com.leeseungyun1020.learningtrip.model.Keyword
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.leeseungyun1020.learningtrip.network.loadNetworkData

class HomeRepository {
    val recommendedKeywords = MutableLiveData<List<Keyword>>()
    val mainBanners = MutableLiveData<List<Banner>>()
    val recommendedPlaces = MutableLiveData<List<SimplePlace>>()
    val recommendedCourses = MutableLiveData<List<SimpleCourse>>()

    private fun loadRecommendedKeywords() {
        RetrofitClient.homeService.getRecommendKeyword().loadNetworkData(
            target = recommendedKeywords
        )
    }

    private fun loadMainBanners() {
        RetrofitClient.homeService.getBanner().loadNetworkData(
            target = mainBanners
        )
    }

    private fun loadRecommendedPlaces() {
        RetrofitClient.homeService.getRecommendPlace().loadNetworkData(
            target = recommendedPlaces
        )
    }

    private fun loadRecommendedCourses() {
        RetrofitClient.homeService.getRecommendCourse().loadNetworkData(
            target = recommendedCourses,
            printLog = true
        )
    }

    fun loadHomeContents() {
        loadRecommendedKeywords()
        loadMainBanners()
        loadRecommendedPlaces()
        loadRecommendedCourses()
    }
}