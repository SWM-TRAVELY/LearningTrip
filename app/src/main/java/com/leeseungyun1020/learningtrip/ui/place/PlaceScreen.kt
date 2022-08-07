package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
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
import com.leeseungyun1020.learningntripapitest.SimpleHeritage
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.PlaceReview
import com.leeseungyun1020.learningtrip.placeViewModel
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.HeritageBox
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.Gray4
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

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .background(color = Gray4)
                        )

                        Text(
                            text = stringResource(id = R.string.title_related_heritage),
                            modifier = Modifier.padding(top = 10.dp, start = 16.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )

                        val heritages = listOf(
                            SimpleHeritage(
                                id = 1,
                                name = "문화재1",
                                imageURL = "https://upload.wikimedia.org/wikipedia/commons/e/e2/Cheomseongdae-1.jpg"
                            ),
                            SimpleHeritage(
                                id = 2,
                                name = "문화재1",
                                imageURL = "https://upload.wikimedia.org/wikipedia/commons/e/e2/Cheomseongdae-1.jpg"
                            ),
                            SimpleHeritage(
                                id = 3,
                                name = "문화재1",
                                imageURL = "https://upload.wikimedia.org/wikipedia/commons/e/e2/Cheomseongdae-1.jpg"
                            ),
                            SimpleHeritage(
                                id = 4,
                                name = "문화재1",
                                imageURL = "https://upload.wikimedia.org/wikipedia/commons/e/e2/Cheomseongdae-1.jpg"
                            ),
                            SimpleHeritage(
                                id = 5,
                                name = "문화재1",
                                imageURL = "https://upload.wikimedia.org/wikipedia/commons/e/e2/Cheomseongdae-1.jpg"
                            ),
                        )

                        LazyRow(modifier = Modifier.padding(top = 10.dp)) {
                            items(count = heritages.size, key = { heritages[it].id }) {
                                val modifier = if (it == 0)
                                    Modifier.padding(start = 16.dp)
                                else Modifier
                                HeritageBox(
                                    modifier = modifier
                                        .padding(end = 8.dp)
                                        .width(120.dp)
                                        .height(150.dp)
                                        .clickable { navController.navigate("${Screen.Heritage.root}/${heritages[it].id}") },
                                    simpleHeritage = heritages[it]
                                )
                            }
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