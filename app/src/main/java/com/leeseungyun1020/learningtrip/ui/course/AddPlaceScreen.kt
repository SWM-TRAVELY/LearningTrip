package com.leeseungyun1020.learningtrip.ui.course

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.data.AppDatabase
import com.leeseungyun1020.learningtrip.data.PlaceRepository
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.home.PlaceListView
import com.leeseungyun1020.learningtrip.ui.theme.Gray3
import com.leeseungyun1020.learningtrip.viewmodel.AddCourseViewModel
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModel
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlaceScreen(
    navController: NavController,
    viewModel: AddCourseViewModel = viewModel()
) {
    val database = AppDatabase.getDatabase(LocalContext.current)
    val repository = PlaceRepository(database.placeDao())
    val placeViewModel: PlaceViewModel = viewModel(
        factory = PlaceViewModelFactory(repository)
    )

    var searchText by rememberSaveable { mutableStateOf("") }
    val placeList by placeViewModel.filteredPlaces.observeAsState()

    LearningTripScaffold(
        title = stringResource(id = R.string.title_add_place),
        topBarExtraContent = {
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    placeViewModel.placeByKeyword(it)
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        placeViewModel.placeByKeyword(searchText)
                    },
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .padding(
                        top = 8.dp
                    )
                    .fillMaxWidth(),
                placeholder = {
                    Row {
                        Icon(Icons.Filled.Search, stringResource(id = R.string.action_search))
                        Spacer(modifier = Modifier.padding(start = 4.dp))
                        Text(
                            text = stringResource(id = R.string.hint_search),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                },
                singleLine = true,
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        IconButton(onClick = { searchText = "" }) {
                            Icon(
                                Icons.Filled.Clear,
                                stringResource(id = R.string.action_delete)
                            )
                        }
                    }
                },

                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White,
                    placeholderColor = Gray3
                ),
                shape = RoundedCornerShape(10.dp)
            )
        }
    ) {
        PlaceListView(
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
            innerPadding = PaddingValues(top = 10.dp, start = 4.dp, end = 4.dp),
            placeList = placeList ?: listOf(),
            onPlaceClicked = {
                viewModel.modifiedCourseList.add(it)
                navController.popBackStack()
            })
    }
}