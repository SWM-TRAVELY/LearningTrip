package com.leeseungyun1020.learningtrip.network

import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.model.course.CourseResponse
import retrofit2.http.*

interface CourseService {
    @GET("/course/{id}")
    fun getCourse(
        @Path("id") id: Int
    ): retrofit2.Call<Course>

    @GET("/course/user/{id}")
    fun getUserCourse(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): retrofit2.Call<Course>

    @GET("/course/list")
    fun getUserCourseList(
        @Header("Authorization") token: String
    ): retrofit2.Call<List<SimpleCourse>>

    @Headers("Accept:application/json", "Content-Type: application/json")
    @POST("/course")
    fun addCourse(
        @Body course: Course,
        @Header("Authorization") token: String
    ): retrofit2.Call<CourseResponse>

    @Headers("Accept:application/json", "Content-Type: application/json")
    @PUT("/course")
    fun updateCourse(
        @Body course: Course,
        @Header("Authorization") token: String
    ): retrofit2.Call<CourseResponse>

    @Headers("Accept:application/json", "Content-Type: application/json")
    @HTTP(method = "DELETE", path = "/course", hasBody = true)
    fun deleteCourse(
        @Body course: Course,
        @Header("Authorization") token: String
    ): retrofit2.Call<CourseResponse>
}