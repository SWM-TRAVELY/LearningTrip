package com.leeseungyun1020.learningtrip.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.*
import com.leeseungyun1020.learningtrip.data.CourseRepository
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.DetailedCourse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CourseViewModel(private val repository: CourseRepository) : ViewModel() {
    val courseList: Flow<List<Course>> = repository.all
    val courseDetailList: SnapshotStateList<DetailedCourse> =
        listOf<DetailedCourse>().toMutableStateList()

    fun loadCourseDetailList() = viewModelScope.launch(Dispatchers.IO) {
        courseList.map { courseList ->
            courseList.map { course ->
                DetailedCourse(
                    id = course.id,
                    name = course.name,
                    placeList = repository.getPlaceList(course.id)
                )
            }
        }.collect { courseDetailList.addAll(it) }
    }

    fun getPlaceById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getPlaceById(id)
    }
}

class CourseViewModelFactory(private val repository: CourseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}