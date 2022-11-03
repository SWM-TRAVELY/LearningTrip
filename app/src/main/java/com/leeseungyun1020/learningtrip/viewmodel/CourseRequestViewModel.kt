package com.leeseungyun1020.learningtrip.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.material.datepicker.MaterialDatePicker

class CourseRequestViewModel : ViewModel() {
    private var _start by mutableStateOf(MaterialDatePicker.todayInUtcMilliseconds())
    val start: Long
        get() = _start

    private var _end by mutableStateOf(MaterialDatePicker.todayInUtcMilliseconds())
    val end: Long
        get() = _end

    fun onUpdateRange(start: Long, end: Long) {
        _start = start
        _end = end
    }
}