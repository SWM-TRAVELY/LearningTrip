package com.leeseungyun1020.learningtrip.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.model.DetailedCourse
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.model.toCourse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCourseViewModel() : ViewModel() {
    var course = DetailedCourse(
        1, "새 코스", emptyList()
    )
    private val _modifiedCourseList = course.placeList.toMutableStateList()
    val modifiedCourseList
        get() = _modifiedCourseList.toList()
    var courseName by mutableStateOf(course.name)

    fun loadCourse(id: Int) = viewModelScope.launch(Dispatchers.IO) {

    }

    fun addPlace(place: SimplePlace) {
        _modifiedCourseList.add(place)
    }

    fun swapPlace(i: Int) {
        val place = _modifiedCourseList.removeAt(i)
        _modifiedCourseList.add(i + 1, place)
    }

    fun updateCourse() = viewModelScope.launch(Dispatchers.IO) {

    }

    fun deleteCourse() = viewModelScope.launch(Dispatchers.IO) {

    }
}