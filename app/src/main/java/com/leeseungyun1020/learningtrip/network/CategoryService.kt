package com.leeseungyun1020.learningtrip.network

import com.leeseungyun1020.learningtrip.model.course.GroupItem
import retrofit2.http.GET

interface CategoryService {
    @GET("/category")
    fun getCategory(): retrofit2.Call<List<GroupItem>>
}