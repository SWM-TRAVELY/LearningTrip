package com.leeseungyun1020.learningtrip.data

import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.leeseungyun1020.learningtrip.network.loadAuthRequiredNetworkData
import com.leeseungyun1020.learningtrip.network.loadNetworkData
import com.leeseungyun1020.learningtrip.network.sendAuthRequiredData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CourseRepository {
    val searchedCourse = MutableLiveData<Course>()
    val storyCourses = MutableLiveData<List<SimpleCourse>>()

    suspend fun searchById(id: Int) {
        withContext(Dispatchers.IO) {
            RetrofitClient.courseService.getCourse(id).loadNetworkData(
                target = searchedCourse
            )
        }
    }

    suspend fun loadCourseList(token: String, onReloadRequired: () -> Unit) {
        withContext(Dispatchers.IO) {
            RetrofitClient.courseService.getUserCourseList(token).loadAuthRequiredNetworkData(
                target = storyCourses,
                null,
                onReloadRequired
            )
        }
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
}