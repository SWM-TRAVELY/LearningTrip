package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.model.Banner
import com.leeseungyun1020.learningtrip.model.Keyword
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    init {
        loadHomeContents()
    }

    val recommendedKeywords = MutableLiveData<List<Keyword>>()
    val mainBanners = MutableLiveData<List<Banner>>()
    val recommendedPlaces = MutableLiveData<List<SimplePlace>>()
    val recommendedCourses = MutableLiveData<List<SimpleCourse>>()


    fun loadHomeContents() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            // 추천 키워드
            RetrofitClient.homeService.getRecommendKeyword()
                .enqueue(object : Callback<List<Keyword>> {
                    override fun onFailure(call: Call<List<Keyword>>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<List<Keyword>>,
                        response: Response<List<Keyword>>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                recommendedKeywords.postValue(body)
                            }
                        }
                    }
                })
            // 메인 배너
            RetrofitClient.homeService.getBanner()
                .enqueue(object : Callback<List<Banner>> {
                    override fun onFailure(call: Call<List<Banner>>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<List<Banner>>,
                        response: Response<List<Banner>>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                mainBanners.postValue(body)
                            }
                        }
                    }
                })
            // 추천 관광지
            RetrofitClient.homeService.getRecommendPlace()
                .enqueue(object : Callback<List<SimplePlace>> {
                    override fun onFailure(call: Call<List<SimplePlace>>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<List<SimplePlace>>,
                        response: Response<List<SimplePlace>>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                recommendedPlaces.postValue(body)
                            }
                        }
                    }
                })
            // 추천 코스
            RetrofitClient.homeService.getRecommendCourse()
                .enqueue(object : Callback<List<SimpleCourse>> {
                    override fun onFailure(call: Call<List<SimpleCourse>>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<List<SimpleCourse>>,
                        response: Response<List<SimpleCourse>>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                recommendedCourses.postValue(body)
                            }
                        }
                    }
                })
        }
    }
}