package com.leeseungyun1020.learningtrip.ui.course

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.data.AuthRepository
import com.leeseungyun1020.learningtrip.model.SimpleCoursePlace
import com.leeseungyun1020.learningtrip.model.toSimplePlace
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModel
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModelFactory
import com.leeseungyun1020.learningtrip.viewmodel.CourseViewModel

@Composable
fun CourseScreen(
    navController: NavController,
    id: String,
    authViewModel: AuthViewModel,
    courseViewModel: CourseViewModel = viewModel()
) {
    if (id.isDigitsOnly()) {
        courseViewModel.searchById(id.toInt())
    }
    val course by courseViewModel.course.observeAsState()

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

            var prev = 0
            for ((i, place) in (course?.placeList ?: emptyList()).sortedWith(
                compareBy(
                    SimpleCoursePlace::day,
                    SimpleCoursePlace::sequence
                )
            ).withIndex()) {
                if (place.day != null && place.day > prev) {
                    prev = place.day
                    Text(
                        text = place.day.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                }
                PlaceLocationBox(
                    simplePlace = place.toSimplePlace(), modifier = Modifier
                        .padding(
                            start = 16.dp, end = 20.dp, top = if (i == 0) 18.dp else 30.dp
                        )
                        .clickable { navController.navigate("${Screen.Place.root}/${place.id}") }
                )
            }

            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 36.dp)
            ) {
                Button(
                    onClick = { navController.navigate("${Screen.AddCourse.root}/${id}") },
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Text(text = stringResource(id = R.string.action_update_course))
                }

                Button(
                    onClick = {
                        val token = authViewModel.token
                        if (token != null)
                            course?.let {
                                courseViewModel.deleteCourse(it, token)
                                navController.popBackStack()
                            }
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                ) {
                    Text(text = stringResource(id = R.string.action_delete_course))
                }
            }


        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CourseScreenPrev() {
    val authRepository = AuthRepository(LocalContext.current)
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(authRepository)
    )
    CourseScreen(navController = rememberNavController(), "1", authViewModel)
}