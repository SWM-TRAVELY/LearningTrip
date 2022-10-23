package com.leeseungyun1020.learningtrip.ui.course

import android.util.Log
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.data.AuthRepository
import com.leeseungyun1020.learningtrip.data.TAG
import com.leeseungyun1020.learningtrip.model.SimpleCoursePlace
import com.leeseungyun1020.learningtrip.model.toSimplePlace
import com.leeseungyun1020.learningtrip.ui.NavigationScreen
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.viewmodel.AddCourseViewModel
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModel
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModelFactory

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddCourseScreen(
    navController: NavController,
    id: String,
    authViewModel: AuthViewModel,
    isCopy: Boolean = false,
    addCourseViewModel: AddCourseViewModel = viewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var isLoaded by remember { mutableStateOf(false) }
    var isInit by remember { mutableStateOf(false) }

    if ((id.toIntOrNull() ?: -1) > 0 && !isLoaded) {
        addCourseViewModel.loadCourse(id.toInt())
        isLoaded = true
    }

    val searchedCourse by addCourseViewModel.searchedCourse.observeAsState()
    if (searchedCourse != null && !isInit) {
        searchedCourse?.run {
            addCourseViewModel.initCourse(this, isCopy)
            isInit = true
        }
    }

    LearningTripScaffold(
        title = stringResource(id = R.string.title_update_course),
    ) {
        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            OutlinedTextField(
                value = addCourseViewModel.courseName,
                onValueChange = {
                    addCourseViewModel.courseName = it
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

            val placeListByDay = addCourseViewModel.modifiedCourseList.groupBy { it.day }
            for (day in 1..addCourseViewModel.maxDay) {
                val list = placeListByDay[day]?.sortedBy(SimpleCoursePlace::sequence) ?: emptyList()
                Text(
                    text = day.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Log.d(
                    TAG,
                    "${list.sortedBy(SimpleCoursePlace::sequence).map { it.name to it.sequence }}"
                )
                for ((i, place) in list.withIndex()) {
                    var expanded by remember { mutableStateOf(false) }
                    Box {
                        PlaceLocationBox(simplePlace = place.toSimplePlace(), modifier = Modifier
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
                                        addCourseViewModel.removePlace(place)
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

                    if (i < list.lastIndex) IconButton(
                        onClick = {
                            addCourseViewModel.swapPlace(place, list[i + 1])
                            Log.d(
                                TAG,
                                "AddCourseScreen: SWAP ${place.name to place.sequence} ${list[i + 1].name to list[i + 1].sequence}"
                            )
                        }, modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            imageVector = Icons.Default.SwapVert,
                            contentDescription = stringResource(
                                id = R.string.action_swap
                            )
                        )
                    }
                }
                IconButton(
                    onClick = {
                        navController.navigate("${Screen.AddPlace.root}/$day/${list.size + 1}")
                    }, modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = stringResource(
                            id = R.string.action_add
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 36.dp)
            ) {
                Button(
                    onClick = {
                        addCourseViewModel.addMaxDay()
                    }, modifier = Modifier
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.action_add_day),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Button(
                    onClick = {
                        val token = authViewModel.token
                        if (token != null) {
                            addCourseViewModel.updateCourse(token)
                            navController.popBackStack()
                            if (isCopy)
                                navController.navigate(NavigationScreen.Story.route)
                        }
                    }, modifier = Modifier
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.action_save_course),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddCourseScreenPrev() {
    val authRepository = AuthRepository(LocalContext.current)
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(authRepository)
    )
    AddCourseScreen(rememberNavController(), "1", authViewModel)
}