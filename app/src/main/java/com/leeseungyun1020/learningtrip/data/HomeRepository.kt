package com.leeseungyun1020.learningtrip.data

import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.Banner
import com.leeseungyun1020.learningtrip.model.Keyword
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.leeseungyun1020.learningtrip.network.loadNetworkData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository {
    val recommendedKeywords = MutableLiveData<List<Keyword>>()
    val mainBanners = MutableLiveData<List<Banner>>()
    val recommendedPlaces = MutableLiveData<List<SimplePlace>>()
    val recommendedCourses = MutableLiveData<List<SimpleCourse>>()

    suspend fun loadRecommendedKeywords() {
        withContext(Dispatchers.IO) {
            RetrofitClient.homeService.getRecommendKeyword().loadNetworkData(
                target = recommendedKeywords
            )
        }
    }

    suspend fun loadMainBanners() {
        withContext(Dispatchers.IO) {
            RetrofitClient.homeService.getBanner().loadNetworkData(
                target = mainBanners
            )
        }
    }

    suspend fun loadRecommendedPlaces() {
        withContext(Dispatchers.IO) {
            RetrofitClient.homeService.getRecommendPlace().loadNetworkData(
                target = recommendedPlaces
            )
        }
    }

    suspend fun loadRecommendedCourses() {
        withContext(Dispatchers.IO) {
            RetrofitClient.homeService.getRecommendCourse().loadNetworkData(
                target = recommendedCourses
            )
        }
    }

    suspend fun loadHomeContents() {
        loadRecommendedKeywords()
        loadMainBanners()
        loadRecommendedPlaces()
        loadRecommendedCourses()
    }
}