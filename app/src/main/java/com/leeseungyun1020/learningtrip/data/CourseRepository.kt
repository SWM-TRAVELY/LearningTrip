package com.leeseungyun1020.learningtrip.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.model.course.CourseOptionResponse
import com.leeseungyun1020.learningtrip.model.course.CourseRequestOption
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.leeseungyun1020.learningtrip.network.loadAuthRequiredNetworkData
import com.leeseungyun1020.learningtrip.network.loadNetworkData
import com.leeseungyun1020.learningtrip.network.sendAuthRequiredData

class CourseRepository {
    val searchedCourse = MutableLiveData<Course>()
    val storyCourses = MutableLiveData<List<SimpleCourse>>()
    val recommendedCourses = MutableLiveData<List<SimpleCourse>>()
    private val _options = MutableLiveData<CourseOptionResponse>()
    val options: LiveData<CourseOptionResponse>
        get() = _options
    val searchedKeywordList = MutableLiveData<List<String>>()

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

    fun loadRecommendedCourseList(
        start: Long,
        end: Long,
        location: String,
        locationOption: String,
        grade: String,
        gradeOption: String,
        keyword: List<String>
    ) {
        RetrofitClient.courseService.requestRecommendCourse(
            CourseRequestOption(
                start,
                end,
                location,
                locationOption,
                grade,
                gradeOption,
                keyword
            )
        ).loadNetworkData(
            target = recommendedCourses
        )
    }

    fun loadOptions(init: (CourseOptionResponse?) -> Unit) {
        RetrofitClient.courseService.getCourseOptions().loadNetworkData(
            target = _options,
            onSuccess = init
        )
    }

    fun loadSearchedKeywordList(keyword: String) {
        RetrofitClient.searchService.getSearchedCourseKeywordOptionList(keyword).loadNetworkData(
            target = searchedKeywordList
        )
    }
}