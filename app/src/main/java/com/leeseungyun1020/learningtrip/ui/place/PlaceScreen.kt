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
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.PlaceReview
import com.leeseungyun1020.learningtrip.model.SimpleHeritage
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.HeritageBox
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.Gray4
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModel

@Composable
fun PlaceScreen(navController: NavController, placeViewModel: PlaceViewModel, id: String) {
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
                                name = "다보탑",
                                imageURL = "http://www.cha.go.kr/unisearch/images/national_treasure/1612673.jpg"
                            ),
                            SimpleHeritage(
                                id = 2,
                                name = "석가탑",
                                imageURL = "https://www.heritage.go.kr/unisearch/images/national_treasure/thumb/2021070210514100.jpg"
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

                        val relatedPlaces = listOf(
                            SimplePlace(
                                id = 126216,
                                typeId = 12,
                                name = "경주 석굴암",
                                imageURL = "http://tong.visitkorea.or.kr/cms/resource/21/2616821_image2_1.jpg"
                            ),
                            SimplePlace(
                                id = 317503,
                                typeId = 12,
                                name = "분황사",
                                imageURL = "http://tong.visitkorea.or.kr/cms/resource/28/2371528_image2_1.jpg"
                            ),
                            SimplePlace(
                                id = 126175,
                                typeId = 12,
                                name = "해인사(합천)",
                                imageURL = "http://tong.visitkorea.or.kr/cms/resource/48/2690648_image2_1.jpg"
                            ),
                        )

                        PlaceListRow(
                            modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                            title = stringResource(id = R.string.title_similar_place),
                            placeStartPadding = 16.dp,
                            places = relatedPlaces,
                            onPlaceClick = { navController.navigate("${Screen.Place.root}/${it.id}") }
                        )

                        val nearByPlaces = listOf(
                            SimplePlace(
                                id = 128526,
                                typeId = 12,
                                name = "동궁과 월지",
                                imageURL = "http://tong.visitkorea.or.kr/cms/resource/61/2612561_image2_1.jpg"
                            ),
                            SimplePlace(
                                id = 2658227,
                                typeId = 12,
                                name = "경주 황리단길",
                                imageURL = "https://www.gyeongju.go.kr/upload/content/thumb/20200320/157B106CC0A14796BB0381097F4B64A5.jpg"
                            ),
                            SimplePlace(
                                id = 1492402,
                                typeId = 12,
                                name = "경주 대릉원 일원",
                                imageURL = "https://www.gyeongju.go.kr/upload/content/thumb/20200317/8206FFCD7CB5400DAA963F3CA1504EFF.png"
                            ),
                        )

                        PlaceListRow(
                            modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                            title = stringResource(id = R.string.title_nearby_place),
                            placeStartPadding = 16.dp,
                            places = nearByPlaces,
                            onPlaceClick = { navController.navigate("${Screen.Place.root}/${it.id}") }
                        )
                    }
                },
                @Composable {
                    Text(text = "Review")
                }
            )
        )


    }
}