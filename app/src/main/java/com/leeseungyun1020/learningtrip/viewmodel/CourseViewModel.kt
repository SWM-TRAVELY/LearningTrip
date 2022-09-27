package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseViewModel : ViewModel() {

    private val _courseList = MutableLiveData<List<SimpleCourse>>()
    val courseList: LiveData<List<SimpleCourse>>
        get() = _courseList

    private val _course = MutableLiveData<Course>()
    val course: LiveData<Course>
        get() = _course

    init {
        loadRecommendedCourseList()
        //TODO: 사용자 추가 제거 가능하게 변경
    }

    fun loadCourseById(id: Int) {
        RetrofitClient.courseService.getCourse(id)
            .enqueue(object : Callback<Course> {
                override fun onFailure(call: Call<Course>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<Course>,
                    response: Response<Course>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        val body = response.body()
                        if (body != null) {
                            _course.postValue(body)
                        }
                    }
                }
            })
    }

    fun loadRecommendedCourseList() {
        RetrofitClient.homeService.getRecommendCourse()
            .enqueue(object : Callback<List<SimpleCourse>> {
                override fun onFailure(call: Call<List<SimpleCourse>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<SimpleCourse>>,
                    response: Response<List<SimpleCourse>>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        val body = response.body()
                        if (body != null) {
                            _courseList.postValue(body)
                        }
                    }
                }
            })
    }
}