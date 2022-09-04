package com.leeseungyun1020.learningtrip.ui.course

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.viewmodel.AddCourseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourseScreen(
    navController: NavController,
    id: String,
    viewModel: AddCourseViewModel = viewModel()
) {
    LearningTripScaffold(
        title = stringResource(id = R.string.title_update_course),
    ) {
        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            OutlinedTextField(
                value = viewModel.courseName,
                onValueChange = {
                    viewModel.courseName = it
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )

            for ((i, place) in viewModel.modifiedCourseList.withIndex()) {
                PlaceLocationBox(
                    simplePlace = place,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 20.dp,
                        top = if (i == 0) 18.dp else 30.dp
                    )
                )
                if (i < viewModel.modifiedCourseList.lastIndex)
                    IconButton(
                        onClick = {
                            val place = viewModel.modifiedCourseList.removeAt(i)
                            viewModel.modifiedCourseList.add(i + 1, place)

                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            imageVector = Icons.Default.SwapVert,
                            contentDescription = stringResource(
                                id = R.string.action_swap
                            )
                        )
                    }
                else
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.AddPlace.route)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(
                                id = R.string.action_add
                            )
                        )
                    }
            }
            Button(
                onClick = {
                    val savingCourse = Course(
                        viewModel.course.id,
                        viewModel.courseName,
                        viewModel.modifiedCourseList
                    )
                    // TODO: Save course
                    navController.popBackStack()
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 36.dp)
            ) {
                Text(text = stringResource(id = R.string.action_save_course))
            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddCourseScreenPrev() {
    AddCourseScreen(rememberNavController(), "1")
}