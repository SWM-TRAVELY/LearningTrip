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
import kotlin.math.max

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
    private var isInit = false

    private var _maxDay by mutableStateOf(1)
    val maxDay
        get() = _maxDay

    fun initCourse(newCourse: Course, isCopy: Boolean) {
        if (!isInit) {
            course = if (isCopy) Course(null, newCourse.name, newCourse.placeList) else newCourse
            course.placeList?.let { _modifiedCourseList?.addAll(it) }
            courseName = course.name ?: ""
            _maxDay = max(maxDay, course.placeList?.maxOfOrNull { it.day ?: 1 } ?: 1)
            isInit = true
        }
    }

    fun loadCourse(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.searchById(id)
    }

    fun addPlace(place: SimpleCoursePlace) {
        _modifiedCourseList?.add(place)
    }

    fun swapPlace(place: SimpleCoursePlace, next: SimpleCoursePlace) {
        val delPlace = _modifiedCourseList?.remove(place)
        val delNext = _modifiedCourseList?.remove(next)
        if (delPlace == true && delNext == true) {
            _modifiedCourseList?.add(place.copy(day = next.day, sequence = next.sequence))
            _modifiedCourseList?.add(next.copy(day = place.day, sequence = place.sequence))
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

    fun addMaxDay() {
        _maxDay++
    }
}