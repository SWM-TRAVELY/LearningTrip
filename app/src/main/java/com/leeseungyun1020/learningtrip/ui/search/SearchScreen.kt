package com.leeseungyun1020.learningtrip.ui.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.placeViewModel
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.home.PlaceListView
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme

@Composable
fun SearchScreen(navController: NavController, key: String) {
    val searchResultPlaces by placeViewModel.filteredPlaces.observeAsState()
    placeViewModel.placeByKeyword(key)

    LearningTripScaffold(
        title = key,
        topBarExtraContent = {},
        bodyContent = {
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
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearningTripTheme {
        SearchScreen(navController = rememberNavController(), "keyword")
    }
}