package com.leeseungyun1020.learningtrip.network

import com.leeseungyun1020.learningtrip.model.Place
import com.leeseungyun1020.learningtrip.model.SimplePlace
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceService {
    @GET("/place/{id}")
    fun getPlace(
        @Path("id") id: Int
    ): retrofit2.Call<Place>

    @GET("/place/recommend")
    fun getRecommendPlaceList(): retrofit2.Call<List<SimplePlace>>
}