package com.leeseungyun1020.learningtrip.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.leeseungyun1020.learningtrip.data.CourseRepository
import com.leeseungyun1020.learningtrip.data.TAG
import com.leeseungyun1020.learningtrip.model.SimpleCourse

class CourseRequestViewModel(private val repository: CourseRepository = CourseRepository()) :
    ViewModel() {
    val options = repository.options
    private val _searchedKeywordList = repository.searchedKeywordList
    val searchedKeywordList: LiveData<List<String>>
        get() = _searchedKeywordList

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

    private var _keywordList by mutableStateOf(emptyList<String>())
    val keywordList: List<String>
        get() = _keywordList

    private val _courseList = repository.recommendedCourses
    val courseList: LiveData<List<SimpleCourse>>
        get() = _courseList

    fun onUpdateRange(start: Long, end: Long) {
        if (start >= MaterialDatePicker.todayInUtcMilliseconds()) {
            _start = start
            _end = end
        }
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

    fun loadOptions() {
        repository.loadOptions { res ->
            res?.let {
                _location = it.locations.firstOrNull()?.name ?: _location
                _locationOption = it.locations.firstOrNull()?.options?.firstOrNull() ?: ""
                _grade = it.grades.firstOrNull()?.name ?: _grade
                _gradeOption = it.grades.firstOrNull()?.options?.firstOrNull() ?: ""
            }
        }
    }

    fun onKeywordChange(keyword: String) {
        repository.loadSearchedKeywordList(keyword)
    }

    fun onKeywordAdd(keyword: String) {
        if (keyword !in _keywordList)
            _keywordList += keyword
    }

    fun onKeywordDelete(keyword: String) {
        _keywordList -= keyword
    }

    fun onKeywordClear() {
        _searchedKeywordList.value = emptyList()
    }

    fun onRequestCourse(move: () -> Unit) {
        Log.d(
            TAG,
            "onRequestCourse: $start, $end, $location, $locationOption, $grade, $gradeOption, $keywordList"
        )
        if (_locationOption.isNotEmpty() && _gradeOption.isNotEmpty()) {
            repository.loadRecommendedCourseList(
                start,
                end,
                location,
                locationOption,
                grade,
                gradeOption,
                keywordList
            )
            move()
        }
    }

}