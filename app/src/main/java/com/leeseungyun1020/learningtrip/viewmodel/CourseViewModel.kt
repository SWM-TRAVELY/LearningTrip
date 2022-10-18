package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.data.CourseRepository
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import kotlinx.coroutines.launch

class CourseViewModel(private val repository: CourseRepository = CourseRepository()) : ViewModel() {

    private val _courseList = repository.storyCourses
    val courseList: LiveData<List<SimpleCourse>>
        get() = _courseList

    private val _course = repository.searchedCourse
    val course: LiveData<Course>
        get() = _course

    fun searchById(id: Int) = viewModelScope.launch {
        repository.searchById(id)
    }

    fun loadCourseList(token: String) = viewModelScope.launch {
        repository.loadCourseList(token)
    }
}