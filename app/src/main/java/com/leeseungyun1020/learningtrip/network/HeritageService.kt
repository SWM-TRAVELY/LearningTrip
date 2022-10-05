package com.leeseungyun1020.learningtrip.network

import com.leeseungyun1020.learningtrip.model.Heritage
import com.leeseungyun1020.learningtrip.model.SimpleHeritage
import retrofit2.http.GET
import retrofit2.http.Path

interface HeritageService {
    @GET("/heritage/{id}")
    fun getHeritage(
        @Path("id") id: Int
    ): retrofit2.Call<Heritage>

    @GET("/heritage/related/{placeId}")
    fun getRelatedHeritage(
        @Path("placeId") placeId: Int
    ): retrofit2.Call<List<SimpleHeritage>>
}