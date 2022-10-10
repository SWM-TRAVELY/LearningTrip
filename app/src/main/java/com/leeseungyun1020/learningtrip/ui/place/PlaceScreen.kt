package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.PlaceReview
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.HeritageBox
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.Gray4
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModel

@Composable
fun PlaceScreen(navController: NavController, placeViewModel: PlaceViewModel, id: String) {
    val place by placeViewModel.placeById.observeAsState()
    val relatedHeritages by placeViewModel.relatedHeritages.observeAsState()
    val relatedPlaces by placeViewModel.relatedPlaces.observeAsState()
    val nearbyPlaces by placeViewModel.nearbyPlaces.observeAsState()
    var isDescriptionOpen by remember { mutableStateOf(false) }
    var isReviewOpen by remember { mutableStateOf(false) }
    if (id.isDigitsOnly()) {
        placeViewModel.loadPlaceSpecific(id.toInt())
    }
    LearningTripScaffold(
        title = stringResource(id = R.string.app_name),
        setDisplayHomeAsUpEnabled = true,
        onHomeAsUpClicked = { navController.popBackStack() },
        setBodyContentInnerPadding = false,
        topBarExtraContent = {

        }) {

        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(place?.imageURL)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.place_placeholder),
                    error = painterResource(R.drawable.place_placeholder),
                    fallback = painterResource(R.drawable.place_placeholder),
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
                            .padding(top = 232.dp),
                        place = it,
                        placeReview = PlaceReview(place!!.id, 0.0, 0),
                        onPlayClick = { /*TODO*/ },
                        onStickerClick = { /*TODO*/ })
                }
            }

            // TODO: Add place detail and review view
            TopIndicatorTab(
                titles = listOf(
                    stringResource(id = R.string.description),
                    "",/*TODO: stringResource(id = R.string.review)*/
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

                            val heritages = relatedHeritages ?: listOf()
                            if (heritages.isNotEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.title_related_heritage),
                                    modifier = Modifier.padding(top = 10.dp, start = 16.dp),
                                    style = MaterialTheme.typography.bodyLarge
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

                            relatedPlaces?.let {
                                PlaceListRow(
                                    modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                                    title = stringResource(id = R.string.title_similar_place),
                                    placeStartPadding = 16.dp,
                                    places = it,
                                    onPlaceClick = { navController.navigate("${Screen.Place.root}/${it.id}") }
                                )
                            }

                            nearbyPlaces?.let {
                                PlaceListRow(
                                    modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                                    title = stringResource(id = R.string.title_nearby_place),
                                    placeStartPadding = 16.dp,
                                    places = it,
                                    onPlaceClick = { navController.navigate("${Screen.Place.root}/${it.id}") }
                                )
                            }
                        }
                    },
                    @Composable {
                        Text(text = "Review")
                    }
                ),
                isMoveAble = listOf(true, false)
            )
        }
    }
}