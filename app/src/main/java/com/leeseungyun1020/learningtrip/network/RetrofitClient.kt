package com.leeseungyun1020.learningtrip.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val placeService: PlaceService by lazy {
        retrofit.create(PlaceService::class.java)
    }

    val homeService: HomeService by lazy {
        retrofit.create(HomeService::class.java)
    }

    val heritageService: HeritageService by lazy {
        retrofit.create(HeritageService::class.java)
    }

    val courseService: CourseService by lazy {
        retrofit.create(CourseService::class.java)
    }

    val searchService: SearchService by lazy {
        retrofit.create(SearchService::class.java)
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
}