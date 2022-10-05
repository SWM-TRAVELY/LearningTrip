package com.leeseungyun1020.learningtrip.data

import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.leeseungyun1020.learningtrip.network.loadNetworkData
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

    suspend fun loadCourseList() {
        // TODO: 사용자별 코스 리스트로 변경
        withContext(Dispatchers.IO) {
            RetrofitClient.homeService.getRecommendCourse().loadNetworkData(
                target = storyCourses
            )
        }
    }
}