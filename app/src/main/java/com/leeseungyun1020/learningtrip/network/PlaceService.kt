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

    @GET("/place/related/{id}")
    fun getRelatedPlace(
        @Path("id") id: Int
    ): retrofit2.Call<List<SimplePlace>>

    @GET("/place/nearby/{id}")
    fun getNearbyPlace(
        @Path("id") id: Int
    ): retrofit2.Call<List<SimplePlace>>
}