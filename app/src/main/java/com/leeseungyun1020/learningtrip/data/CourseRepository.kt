package com.leeseungyun1020.learningtrip.data

import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.leeseungyun1020.learningtrip.network.loadAuthRequiredNetworkData
import com.leeseungyun1020.learningtrip.network.loadNetworkData
import com.leeseungyun1020.learningtrip.network.sendAuthRequiredData

class CourseRepository {
    val searchedCourse = MutableLiveData<Course>()
    val storyCourses = MutableLiveData<List<SimpleCourse>>()
    val recommendedCourses = MutableLiveData<List<SimpleCourse>>()

    fun searchById(id: Int, isUser: Boolean, token: String? = null) {
        if (!isUser)
            RetrofitClient.courseService.getCourse(id).loadNetworkData(
                target = searchedCourse
            )
        else if (token != null)
            RetrofitClient.courseService.getUserCourse(id, token).loadAuthRequiredNetworkData(
                target = searchedCourse
            )
    }

    fun loadCourseList(token: String, onReloadRequired: () -> Unit) {
        RetrofitClient.courseService.getUserCourseList(token).loadAuthRequiredNetworkData(
            target = storyCourses,
            null,
            onReloadRequired
        )
    }

    fun addCourse(course: Course, token: String) {
        RetrofitClient.courseService.addCourse(course, token).sendAuthRequiredData {

        }
    }

    fun updateCourse(course: Course, token: String) {
        RetrofitClient.courseService.updateCourse(course, token).sendAuthRequiredData {

        }
    }

    fun deleteCourse(course: Course, token: String) {
        RetrofitClient.courseService.deleteCourse(course, token).sendAuthRequiredData {

        }
    }

    fun loadRecommendedCourseList() {
        // TODO: 맞춤 코스 추천 API로 변경
        RetrofitClient.homeService.getRecommendCourse().loadNetworkData(
            target = recommendedCourses
        )
    }
}