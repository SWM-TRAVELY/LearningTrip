package com.leeseungyun1020.learningtrip.network

import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CourseService {
    @GET("/course/{id}")
    fun getCourse(
        @Path("id") id: Int
    ): retrofit2.Call<Course>

    @GET("/course/list")
    fun getUserCourseList(
        @Header("Authorization") token: String
    ): retrofit2.Call<List<SimpleCourse>>


}