package com.leeseungyun1020.learningtrip.network

import com.leeseungyun1020.learningtrip.model.Banner
import com.leeseungyun1020.learningtrip.model.Keyword
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.model.SimplePlace
import retrofit2.http.GET

interface HomeService {
    @GET("/home/banner")
    fun getBanner(): retrofit2.Call<List<Banner>>

    @GET("/home/keyword/recommend")
    fun getRecommendKeyword(): retrofit2.Call<List<Keyword>>

    @GET("/home/place/recommend")
    fun getRecommendPlace(): retrofit2.Call<List<SimplePlace>>

    @GET("/home/course/recommend")
    fun getRecommendCourse(): retrofit2.Call<List<SimpleCourse>>
}