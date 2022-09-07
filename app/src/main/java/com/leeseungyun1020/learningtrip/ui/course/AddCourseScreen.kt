package com.leeseungyun1020.learningtrip.ui.course

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.data.AppDatabase
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.viewmodel.AddCourseViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddCourseScreen(
    navController: NavController, id: String, viewModel: AddCourseViewModel = viewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    if ((id.toIntOrNull() ?: -1) > 0) viewModel.loadCourse(id.toInt())
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
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                placeholder = {
                    Text(text = stringResource(id = R.string.title_new_course))
                },
            )

            for ((i, place) in viewModel.modifiedCourseList.withIndex()) {
                var expanded by remember { mutableStateOf(false) }
                Box {
                    PlaceLocationBox(simplePlace = place, modifier = Modifier
                        .padding(
                            start = 16.dp, end = 20.dp, top = if (i == 0) 18.dp else 30.dp
                        )
                        .clickable {
                            expanded = !expanded
                        })
                    Box(modifier = Modifier.align(Alignment.Center)) {
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            DropdownMenuItem(text = { Text(text = stringResource(id = R.string.action_info)) },
                                onClick = {
                                    navController.navigate("${Screen.Place.root}/${place.id}")
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = stringResource(id = R.string.action_delete)
                                    )
                                })
                            DropdownMenuItem(text = { Text(text = stringResource(id = R.string.action_delete)) },
                                onClick = {
                                    viewModel.removePlace(i)
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(id = R.string.action_delete)
                                    )
                                })
                        }
                    }
                }

                if (i < viewModel.modifiedCourseList.lastIndex) IconButton(
                    onClick = {
                        viewModel.swapPlace(i)

                    }, modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector = Icons.Default.SwapVert, contentDescription = stringResource(
                            id = R.string.action_swap
                        )
                    )
                }
            }

            IconButton(
                onClick = {
                    navController.navigate(Screen.AddPlace.route)
                }, modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = stringResource(
                        id = R.string.action_add
                    )
                )
            }

            Button(
                onClick = {
                    viewModel.updateCourse()
                    navController.popBackStack()
                }, modifier = Modifier
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