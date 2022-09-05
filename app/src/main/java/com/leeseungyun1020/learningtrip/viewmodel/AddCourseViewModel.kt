package com.leeseungyun1020.learningtrip.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.data.CourseRepository
import com.leeseungyun1020.learningtrip.model.DetailedCourse
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.model.toCourse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCourseViewModel(private val repository: CourseRepository) : ViewModel() {
    var course = DetailedCourse(
        1, "새 코스", emptyList()
    )
    private val _modifiedCourseList = course.placeList.toMutableStateList()
    val modifiedCourseList
        get() = _modifiedCourseList.toList()
    var courseName by mutableStateOf(course.name)

    fun loadCourse(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        val simpleCourse = repository.getById(id)
        simpleCourse.placeIdList.map { repository.getPlaceById(it) }.let {
            course = DetailedCourse(simpleCourse.id, simpleCourse.name, it)
            _modifiedCourseList.addAll(it)
        }
    }

    fun addPlace(place: SimplePlace) {
        _modifiedCourseList.add(place)
    }

    fun swapPlace(i: Int) {
        val place = _modifiedCourseList.removeAt(i)
        _modifiedCourseList.add(i + 1, place)
    }

    fun updateCourse() = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(course.toCourse())
    }

    fun deleteCourse() = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(course.toCourse())
    }
}

class AddCourseViewModelFactory(private val repository: CourseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddCourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddCourseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}