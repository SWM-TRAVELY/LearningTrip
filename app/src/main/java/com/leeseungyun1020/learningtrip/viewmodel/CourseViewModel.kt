package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.leeseungyun1020.learningtrip.data.CourseRepository
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimpleCourse

class CourseViewModel(private val repository: CourseRepository = CourseRepository()) : ViewModel() {

    private val _courseList = repository.storyCourses
    val courseList: LiveData<List<SimpleCourse>>
        get() = _courseList

    private val _course = repository.searchedCourse
    val course: LiveData<Course>
        get() = _course

    fun searchById(id: Int, isUser: Boolean, token: String?) {
        repository.searchById(id, isUser, token)
    }

    fun loadCourseList(token: String, onReloadRequired: () -> Unit) {
        repository.loadCourseList(token, onReloadRequired)
    }

    fun deleteCourse(course: Course, token: String) {
        repository.deleteCourse(course, token)
    }
}