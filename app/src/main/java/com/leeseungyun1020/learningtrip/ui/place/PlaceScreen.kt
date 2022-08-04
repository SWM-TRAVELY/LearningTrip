package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.Place
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme
import com.leeseungyun1020.learningtrip.ui.theme.Tertiary

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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(top = 72.dp)
                    .height(96.dp)
                    .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 10.dp, end = 24.dp)
                ) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = stringResource(
                                id = R.string.action_play
                            ),
                            modifier = Modifier
                                .width(14.dp)
                                .height(14.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = stringResource(id = R.string.action_play),
                            fontSize = 12.sp
                        )
                    }

                    TextButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sticker),
                            contentDescription = stringResource(
                                id = R.string.action_sticker
                            ),
                            modifier = Modifier
                                .width(14.dp)
                                .height(14.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = stringResource(id = R.string.action_sticker),
                            fontSize = 12.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 10.dp, start = 30.dp)
                ) {
                    Text(text = place.name, fontSize = 20.sp)
                    Row(modifier = Modifier.padding(top = 2.dp)) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = stringResource(id = R.string.star),
                            modifier = Modifier
                                .width(14.dp)
                                .height(14.dp),
                            tint = Tertiary
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = "5.0", fontSize = 10.sp)
                        Spacer(modifier = Modifier.width(1.dp))
                        Text(text = "(100)", fontSize = 10.sp)
                    }

                }
            }

            Button(onClick = { navController.navigate("${Screen.Heritage.root}/100") }) {
                Text(text = "Heritage 100")
            }
        }

        // TODO: Add place detail and review view

    }
}

@Preview(showBackground = true)
@Composable
fun PlaceScreenPreview() {
    LearningTripTheme {
        PlaceScreen(navController = rememberNavController(), "1940208")
    }
}