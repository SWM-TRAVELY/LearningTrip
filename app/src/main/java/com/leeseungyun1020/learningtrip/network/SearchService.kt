package com.leeseungyun1020.learningtrip.network

import com.leeseungyun1020.learningtrip.model.SimplePlace
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchService {
    @GET("/search/autocomplete/{keyword}")
    fun getSearchedKeyword(
        @Path("keyword") keyword: String
    ): retrofit2.Call<List<String>>

    @GET("/search/result/{keyword}")
    fun getSearchedPlaceList(
        @Path("keyword") keyword: String
    ): retrofit2.Call<List<SimplePlace>>

    @GET("/course/options/keyword/{keyword}")
    fun getSearchedCourseKeywordOptionList(
        @Path("keyword") keyword: String
    ): retrofit2.Call<List<String>>
}