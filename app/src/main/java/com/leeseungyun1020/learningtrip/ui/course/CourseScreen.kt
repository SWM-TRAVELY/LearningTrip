package com.leeseungyun1020.learningtrip.ui.course

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.viewmodel.CourseViewModel

@Composable
fun CourseScreen(
    navController: NavController, id: String, viewModel: CourseViewModel = viewModel()
) {
    viewModel.loadCourseById(id.toIntOrNull() ?: -1)
    val course by viewModel.course.observeAsState()
    LearningTripScaffold(
        title = stringResource(id = R.string.title_course),
    ) {
        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            Text(
                text = course?.name ?: stringResource(id = R.string.title_course_error),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Divider(
                thickness = 2.dp, color = MaterialTheme.colorScheme.secondary
            )


            for ((i, place) in (course?.placeList ?: emptyList()).withIndex()) {
                PlaceLocationBox(
                    simplePlace = place, modifier = Modifier.padding(
                        start = 16.dp, end = 20.dp, top = if (i == 0) 18.dp else 30.dp
                    )
                )
            }
            Button(
                onClick = { navController.navigate("${Screen.AddCourse.root}/${id}") },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 36.dp)
            ) {
                Text(text = stringResource(id = R.string.action_update_course))
            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CourseScreenPrev() {
    CourseScreen(navController = rememberNavController(), "1")
}