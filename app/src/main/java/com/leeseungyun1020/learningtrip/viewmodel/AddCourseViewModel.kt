package com.leeseungyun1020.learningtrip.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimplePlace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCourseViewModel() : ViewModel() {
    var course = Course(
        1, "새 코스", emptyList()
    )
    private val _modifiedCourseList = course.placeList?.toMutableStateList()
    val modifiedCourseList
        get() = _modifiedCourseList?.toList() ?: emptyList()
    var courseName by mutableStateOf(course.name)

    fun loadCourse(id: Int) = viewModelScope.launch(Dispatchers.IO) {

    }

    fun addPlace(place: SimplePlace) {
        _modifiedCourseList?.add(place)
    }

    fun swapPlace(i: Int) {
        val place = _modifiedCourseList?.removeAt(i)
        if (place != null) {
            _modifiedCourseList?.add(i + 1, place)
        }
    }

    fun updateCourse() = viewModelScope.launch(Dispatchers.IO) {

    }

    fun deleteCourse() = viewModelScope.launch(Dispatchers.IO) {

    }
}