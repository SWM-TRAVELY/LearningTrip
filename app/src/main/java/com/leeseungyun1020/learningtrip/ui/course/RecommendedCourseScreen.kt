package com.leeseungyun1020.learningtrip.ui.course

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.CourseBox
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.viewmodel.CourseViewModel

@Composable
fun RecommendedCourseScreen(
    navController: NavController,
    courseViewModel: CourseViewModel = viewModel(),
) {
    val courseList by courseViewModel.courseList.observeAsState()
    LearningTripScaffold(
        title = stringResource(id = R.string.title_recommend_course),
    ) {

        LazyColumn(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            contentPadding = PaddingValues(top = 28.dp)
        ) {
            items(courseList ?: listOf()) { course ->
                CourseBox(course = course, modifier = Modifier
                    .padding(bottom = 12.dp)
                    .clickable {
                        navController.navigate("${Screen.Course.root}/${course.id}")
                    })
            }
        }

    }
}