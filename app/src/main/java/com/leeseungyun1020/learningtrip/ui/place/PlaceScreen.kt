package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.PlaceReview
import com.leeseungyun1020.learningtrip.placeViewModel
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme

@Composable
fun PlaceScreen(navController: NavController, id: String) {
    val place by placeViewModel.placeById.observeAsState()
    var isDescriptionOpen by remember { mutableStateOf(false) }
    var isReviewOpen by remember { mutableStateOf(false) }
    if (id.isDigitsOnly())
        placeViewModel.placeById(id.toInt())
    LearningTripScaffold(
        title = stringResource(id = R.string.app_name),
        setDisplayHomeAsUpEnabled = true,
        onHomeAsUpClicked = { navController.popBackStack() },
        setBodyContentInnerPadding = false,
        topBarExtraContent = {

        }) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(place?.imageURL)
                    .crossfade(true)
                    .build(),
                //TODO: Chnage place placeholder
                placeholder = painterResource(R.drawable.ic_baseline_image_24),
                contentDescription = place?.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            )

            place?.let {
                PlaceSummationBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(top = 72.dp),
                    place = it,
                    placeReview = PlaceReview(place!!.id, 4.88, 100),
                    onPlayClick = { /*TODO*/ },
                    onStickerClick = { /*TODO*/ })
            }
        }

        // TODO: Add place detail and review view
        TopIndicatorTab(
            titles = listOf(
                stringResource(id = R.string.description),
                stringResource(id = R.string.review)
            ), pages = listOf(
                @Composable {
                    Column {
                        place?.let {
                            PlaceDescriptionPage(
                                modifier = Modifier.padding(
                                    top = 10.dp,
                                    start = 32.dp,
                                    end = 32.dp
                                ), place = it,
                                isOpen = isDescriptionOpen,
                                onOpenClicked = { isDescriptionOpen = !isDescriptionOpen }
                            )
                        }
                        Button(onClick = { navController.navigate("${Screen.Heritage.root}/100") }) {
                            Text(text = "Heritage 100")
                        }
                    }
                },
                @Composable {
                    Text(text = "Review")
                }
            )
        )


    }
}

@Preview(showBackground = true)
@Composable
fun PlaceScreenPreview() {
    LearningTripTheme {
        PlaceScreen(navController = rememberNavController(), "1940208")
    }
}