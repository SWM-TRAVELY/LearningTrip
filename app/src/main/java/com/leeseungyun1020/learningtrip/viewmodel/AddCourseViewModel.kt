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
    var searchedCourse = repository.searchedCourse
    var course = Course(
        null, "", emptyList()
    )
    private var _modifiedCourseList = course.placeList?.toMutableStateList()
    val modifiedCourseList
        get() = _modifiedCourseList?.toList() ?: emptyList()
    var courseName by mutableStateOf(course.name ?: "")

    fun initCourse(newCourse: Course) {
        course = newCourse
        _modifiedCourseList = course.placeList?.toMutableStateList()
        courseName = course.name ?: ""
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

    fun updateCourse(token: String) {
        if (course.id == null) {
            repository.addCourse(
                Course(
                    id = null,
                    name = courseName,
                    placeList = modifiedCourseList
                ), token
            )
        } else {
            repository.updateCourse(
                Course(
                    id = course.id,
                    name = courseName,
                    placeList = modifiedCourseList
                ), token
            )
        }
    }
}