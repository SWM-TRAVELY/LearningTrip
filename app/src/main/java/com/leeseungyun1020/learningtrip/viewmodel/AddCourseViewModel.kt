package com.leeseungyun1020.learningtrip.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.data.CourseRepository
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimpleCoursePlace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCourseViewModel(private val repository: CourseRepository = CourseRepository()) :
    ViewModel() {
    var course = Course(
        null, "", emptyList()
    )
    private val _modifiedCourseList = course.placeList?.toMutableStateList()
    val modifiedCourseList
        get() = _modifiedCourseList?.toList() ?: emptyList()
    var courseName by mutableStateOf(course.name ?: "")

    fun initCourse() {

    }

    fun loadCourse(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.searchById(id)
    }

    fun addPlace(place: SimpleCoursePlace) {
        _modifiedCourseList?.add(place)
    }

    fun swapPlace(i: Int) {
        val place = _modifiedCourseList?.removeAt(i)
        val next = _modifiedCourseList?.removeAt(i)
        if (place != null && next != null) {
            _modifiedCourseList?.add(i, place.copy(day = next.day, sequence = next.sequence))
            _modifiedCourseList?.add(i, next.copy(day = place.day, sequence = place.sequence))
        }
    }

    fun removePlace(simpleCoursePlace: SimpleCoursePlace) {
        _modifiedCourseList?.remove(simpleCoursePlace)
    }

    fun updateCourse() = viewModelScope.launch(Dispatchers.IO) {

    }

    fun deleteCourse() = viewModelScope.launch(Dispatchers.IO) {

    }
}