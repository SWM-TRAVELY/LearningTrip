package com.leeseungyun1020.learningtrip.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.leeseungyun1020.learningtrip.model.course.CourseOptionResponse
import com.leeseungyun1020.learningtrip.model.course.GroupItem

class CourseRequestViewModel : ViewModel() {
    private var _start by mutableStateOf(MaterialDatePicker.todayInUtcMilliseconds())
    val start: Long
        get() = _start

    private var _end by mutableStateOf(MaterialDatePicker.todayInUtcMilliseconds())
    val end: Long
        get() = _end

    private var _location by mutableStateOf("")
    val location: String
        get() = _location

    private var _locationOption by mutableStateOf("")
    val locationOption: String
        get() = _locationOption

    private var _grade by mutableStateOf("")
    val grade: String
        get() = _grade

    private var _gradeOption by mutableStateOf("")
    val gradeOption: String
        get() = _gradeOption

    fun onUpdateRange(start: Long, end: Long) {
        _start = start
        _end = end
    }

    fun onUpdateLocation(location: String) {
        _location = location
        _locationOption =
            options.value?.locations?.find { it.name == location }?.options?.firstOrNull() ?: ""
    }

    fun onUpdateLocationOption(locationOption: String) {
        _locationOption = locationOption
    }

    fun onUpdateGrade(grade: String) {
        _grade = grade
        _gradeOption =
            options.value?.grades?.find { it.name == grade }?.options?.firstOrNull() ?: ""
    }

    fun onUpdateGradeOption(gradeOption: String) {
        _gradeOption = gradeOption
    }

    private var _options = MutableLiveData<CourseOptionResponse>()
    val options: LiveData<CourseOptionResponse>
        get() = _options

    fun loadOptions() {
        val result = CourseOptionResponse(
            locations = listOf(
                GroupItem("서울", listOf("서울 전체")),
                GroupItem("부산", listOf("부산 전체")),
            ),
            grades = listOf(
                GroupItem("초등", listOf("초등 1학년", "초등 2학년", "초등 3학년")),
                GroupItem("중등", listOf("중등 1학년", "중등 2학년", "중등 3학년")),
                GroupItem("고등", listOf("고등 1학년", "고등 2학년", "고등 3학년")),
            ),
            keywords = listOf("키워드1", "키워드2", "키워드3", "키워드4")
        )
        _options.value = result
        _location = result.locations.firstOrNull()?.name ?: _location
        _locationOption = result.locations.firstOrNull()?.options?.firstOrNull() ?: ""
        _grade = result.grades.firstOrNull()?.name ?: _grade
        _gradeOption = result.grades.firstOrNull()?.options?.firstOrNull() ?: ""
    }
}