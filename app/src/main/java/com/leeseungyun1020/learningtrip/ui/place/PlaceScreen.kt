package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.Place
import com.leeseungyun1020.learningtrip.model.PlaceReview
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme

@Composable
fun PlaceScreen(navController: NavController, id: String) {
    val place = Place(
        id = 1,
        name = "서울역",
        typeId = 14,
        address = "서울시 중구 을지로동 서울역",
        chkInTextbook = true,
        latitude = 37.55,
        longitude = 126.98,
        tel = "051-000-0000",
        overview = "서울역은 서울시 중구 을지로동 서울역이다.",
        imageURL = "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34",
        restDate = "Rest Date",
        useTime = "Use Time",
        chkParking = false,
        chkBabyCarriage = false,
        chkPets = false,
        ageAvailable = "전연령",
        expGuide = "체험여행자료",
        chkWorldCultural = false,
        chkWorldNatural = false,
        chkWorldRecord = true,
    )

//    val place by placeViewModel.placeById.observeAsState()
//    if (id.isDigitsOnly())
//        placeViewModel.placeById(id.toInt())
//    else
//        Log.e("LSY", "PlaceScreen: $id is not a number")
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
                    .data(place.imageURL)
                    .crossfade(true)
                    .build(),
                //TODO: Chnage place placeholder
                placeholder = painterResource(R.drawable.ic_baseline_image_24),
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            )

            PlaceSummationBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(top = 72.dp),
                place = place,
                placeReview = PlaceReview(place.id, 4.88, 100),
                onPlayClick = { /*TODO*/ },
                onStickerClick = { /*TODO*/ })
        }

        // TODO: Add place detail and review view
        TopIndicatorTab(
            titles = listOf(
                stringResource(id = R.string.description),
                stringResource(id = R.string.review)
            ), pages = listOf(
                @Composable {
                    Button(onClick = { navController.navigate("${Screen.Heritage.root}/100") }) {
                        Text(text = "Heritage 100")
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