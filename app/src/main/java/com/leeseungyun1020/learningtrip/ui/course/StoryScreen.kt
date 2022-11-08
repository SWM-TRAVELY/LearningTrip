package com.leeseungyun1020.learningtrip.ui.course

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.data.AuthRepository
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.account.SignInRequiredScreen
import com.leeseungyun1020.learningtrip.ui.common.CourseBox
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModel
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModelFactory
import com.leeseungyun1020.learningtrip.viewmodel.CourseViewModel

@Composable
fun StoryScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    courseViewModel: CourseViewModel = viewModel(),
) {
    val isSignIn by authViewModel.isSignIn.observeAsState(false)

    if (!isSignIn) {
        SignInRequiredScreen(
            navController = navController,
            name = stringResource(id = R.string.title_story)
        )
    } else {
        val courseList by courseViewModel.courseList.observeAsState()
        val token by authViewModel.token.observeAsState()
        token?.let {
            courseViewModel.loadCourseList(it) { authViewModel.reloadToken() }
        }
        LearningTripScaffold(
            title = stringResource(id = R.string.nav_my_course),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate("${Screen.AddCourse.root}/-1") },
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.action_add)
                    )
                }
            }) {

            val list: MutableList<Any> = courseList?.toMutableList() ?: mutableListOf()
            list.add(0, 0)

            LazyColumn(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                contentPadding = PaddingValues(top = 28.dp)
            ) {
                items(list) { course ->
                    if (course is SimpleCourse) {
                        CourseBox(course = course, modifier = Modifier
                            .padding(bottom = 12.dp)
                            .clickable {
                                navController.navigate("${Screen.Course.root}/${course.id}")
                            })
                    } else {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(MaterialTheme.colorScheme.secondary)
                                .clickable {
                                    navController.navigate(Screen.CourseRequest.route)
                                }
                        ) {
                            Text(
                                text = stringResource(id = R.string.title_request_course),
                                modifier = Modifier.align(Alignment.Center),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary
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
fun StoryScreenPrev() {
    val authRepository = AuthRepository(LocalContext.current)
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(authRepository)
    )
    StoryScreen(navController = rememberNavController(), authViewModel)
}