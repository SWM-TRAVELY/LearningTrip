package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.*
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.model.SimplePlace

class CourseViewModel() : ViewModel() {

    private val _courseList = MutableLiveData<List<SimpleCourse>>()
    val courseList: LiveData<List<SimpleCourse>>
        get() = _courseList

    private val _course = MutableLiveData<Course>()
    val course: LiveData<Course>
        get() = _course

    init {
        _courseList.value = listOf(
            SimpleCourse(
                id = 1,
                name = "코스1",
                imageURL = "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34",
                place1 = "관광지1", place2 = "관광지2", place3 = "관광지3"
            ),
        )
    }

    fun loadCourseById(id: Int) {
        _course.value = Course(
            id, "코스1", listOf(
                SimplePlace(
                    1,
                    "관광지1",
                    "14",
                    "주소",
                    "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34"
                ),
                SimplePlace(
                    2,
                    "관광지2",
                    "14",
                    "주소",
                    "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34"
                ),
            )
        )
    }
}