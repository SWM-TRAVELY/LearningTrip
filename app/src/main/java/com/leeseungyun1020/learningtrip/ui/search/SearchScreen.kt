package com.leeseungyun1020.learningtrip.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.home.PlaceListView
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModel

@Composable
fun SearchScreen(navController: NavController, placeViewModel: PlaceViewModel, key: String) {
    val searchResultPlaces by placeViewModel.filteredPlaces.observeAsState()
    placeViewModel.placeByKeyword(key)

    LearningTripScaffold(
        title = key,
        topBarExtraContent = {},
        setDisplayHomeAsUpEnabled = true,
        onHomeAsUpClicked = { navController.popBackStack() },
        bodyContent = {
            Column(
                modifier = Modifier.verticalScroll(
                    rememberScrollState()
                )
            ) {
                Button(onClick = { navController.navigate("${Screen.Course.root}/200") }) {
                    Text(text = "Course 200")
                }
                PlaceListView(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    innerPadding = PaddingValues(top = 10.dp, start = 4.dp, end = 4.dp),
                    placeList = searchResultPlaces ?: listOf(),
                    onPlaceClicked = {
                        navController.navigate("${Screen.Place.root}/${it.id}")
                    })
            }
        }
    )
}