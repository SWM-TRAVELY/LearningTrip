package com.leeseungyun1020.learningtrip.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimplePlace

class AddCourseViewModel : ViewModel() {
    // TODO: Get course from repository
    val course = Course(
        "1", "코스1", listOf(
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
            SimplePlace(
                3,
                "관광지3",
                "14",
                "주소",
                "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34"
            ),
            SimplePlace(
                4,
                "관광지4",
                "14",
                "주소",
                "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34"
            ),
        )
    )
    val modifiedCourseList = course.placeList.toMutableStateList()
    var courseName by mutableStateOf(course.name)
}

/*
class AddCourseViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddCourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddCourseViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
*/