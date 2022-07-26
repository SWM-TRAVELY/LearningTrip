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
    isEditable: Boolean = true,
    isUser: Boolean = false,
    courseViewModel: CourseViewModel = viewModel()
) {
    val token by authViewModel.token.observeAsState()
    if (id.isDigitsOnly()) {
        courseViewModel.searchById(id.toInt(), isUser, token)
    }
    val course by courseViewModel.course.observeAsState()
    val isSignIn by authViewModel.isSignIn.observeAsState()

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
                    SimpleCoursePlace::day, SimpleCoursePlace::sequence
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
                } else if (place.distance != null && place.time != null) {
                    val hour = place.time / 60
                    val minute = place.time % 60
                    val time = if (hour > 0)
                        "$hour${stringResource(id = R.string.desc_time_hour)} $minute${
                            stringResource(
                                id = R.string.desc_time_minute
                            )
                        }" else "$minute${stringResource(id = R.string.desc_time_minute)}"
                    val distance = if (place.distance >= 1000) "${
                        String.format(
                            "%.2f",
                            place.distance / 1000.0
                        )
                    }km" else "${place.distance}m"
                    Text(
                        text = "$distance ($time)",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        textAlign = TextAlign.Center
                    )
                }
                PlaceLocationBox(simplePlace = place.toSimplePlace(), modifier = Modifier
                    .padding(
                        start = 16.dp, end = 20.dp, top = if (i == 0) 18.dp else 30.dp
                    )
                    .clickable { navController.navigate("${Screen.Place.root}/${place.id}") })
            }

            if (isSignIn == true) {
                Row(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 36.dp)
                ) {
                    Button(
                        onClick = { navController.navigate("${Screen.AddCourse.root}/${id}?isCopy=${!isEditable}&isUser=${isUser}") },
                        modifier = Modifier.padding(horizontal = 4.dp),
                    ) {
                        Text(
                            text = stringResource(id = if (isEditable) R.string.action_update_course else R.string.action_copy_course),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }

                    if (isEditable) {
                        Button(
                            onClick = {

                                token?.let { token ->
                                    course?.let {
                                        courseViewModel.deleteCourse(it, token)
                                        navController.popBackStack()
                                    }
                                }
                            }, modifier = Modifier.padding(horizontal = 4.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.action_delete_course),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
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
    CourseScreen(
        navController = rememberNavController(),
        id = "1",
        authViewModel = authViewModel,
        isEditable = true,
        isUser = false
    )
}