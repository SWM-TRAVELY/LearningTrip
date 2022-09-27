package com.leeseungyun1020.learningtrip.network

import com.leeseungyun1020.learningtrip.model.Course
import retrofit2.http.GET
import retrofit2.http.Path

interface CourseService {
    @GET("/course/{id}")
    fun getCourse(
        @Path("id") id: Int
    ): retrofit2.Call<Course>
}