package com.leeseungyun1020.learningtrip.ui.course

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.data.AppDatabase
import com.leeseungyun1020.learningtrip.data.CourseRepository
import com.leeseungyun1020.learningtrip.model.DetailedCourse
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.CourseBox
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.viewmodel.CourseViewModel
import com.leeseungyun1020.learningtrip.viewmodel.CourseViewModelFactory

@Composable
fun StoryScreen(
    navController: NavController, viewModel: CourseViewModel = viewModel(
        factory = CourseViewModelFactory(
            CourseRepository(AppDatabase.getDatabase(LocalContext.current).courseDao())
        )
    )
) {
    val courseSimpleList by viewModel.courseList.collectAsState(initial = emptyList())
    //val courseList = courseSimpleList.map { viewModel.get }
    val courseList = emptyList<DetailedCourse>()
    LearningTripScaffold(
        title = stringResource(id = R.string.nav_story),
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("${Screen.AddCourse.root}/-1") }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.action_add)
                )
            }
        }) {

        LazyColumn(modifier = Modifier.padding(top = 28.dp, start = 20.dp, end = 20.dp)) {
            items(courseList) { course ->
                CourseBox(detailedCourse = course, modifier = Modifier
                    .padding(bottom = 12.dp)
                    .clickable {
                        navController.navigate("${Screen.Course.root}/${course.id}")
                    })
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StoryScreenPrev() {
    StoryScreen(navController = rememberNavController())
}