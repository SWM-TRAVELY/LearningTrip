package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.Gray3
import com.leeseungyun1020.learningtrip.ui.theme.Gray4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(navController: NavController, placeId: String) {
    LearningTripScaffold(
        title = stringResource(id = R.string.title_add_review),
        setDisplayHomeAsUpEnabled = true,
        onHomeAsUpClicked = { navController.popBackStack() },
    ) {
        val simplePlace = SimplePlace(1, "name", 14, "address", "imageUrl")
        var rating by remember { mutableStateOf(0) }
        var reviewText by remember {
            mutableStateOf("")
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(simplePlace.imageURL)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.place_placeholder),
                error = painterResource(id = R.drawable.place_placeholder),
                contentDescription = simplePlace.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 24.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .height(120.dp),
            )

            Text(
                text = stringResource(id = R.string.desc_add_review),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 26.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            RatingButton(
                rating = rating, onRatingClick = { rating = it },
                modifier = Modifier
                    .padding(top = 14.dp)
                    .align(alignment = Alignment.CenterHorizontally), starSize = 36.dp
            )

            OutlinedTextField(
                value = reviewText, onValueChange = { reviewText = it },
                modifier = Modifier
                    .padding(top = 24.dp, start = 20.dp, end = 20.dp)
                    .height(196.dp)
                    .fillMaxWidth(),
                placeholder = { Text(text = stringResource(id = R.string.hint_add_review)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Gray4,
                    unfocusedBorderColor = Gray4,
                    placeholderColor = Gray3
                ),
            )

            Text(
                text = "${reviewText.length}/300",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Normal),
                modifier = Modifier
                    .padding(top = 5.dp, start = 20.dp, end = 20.dp)
                    .align(alignment = Alignment.End)
            )

            Row(Modifier.padding(horizontal = 20.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("TODO: upload image url")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_camera),
                    error = painterResource(id = R.drawable.ic_camera),
                    contentDescription = stringResource(id = R.string.desc_camera),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding()
                        .width(64.dp)
                        .height(64.dp),
                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("TODO: upload image url")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_camera),
                    error = painterResource(id = R.drawable.ic_camera),
                    contentDescription = stringResource(id = R.string.desc_camera),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .width(64.dp)
                        .height(64.dp),
                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("TODO: upload image url")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_camera),
                    error = painterResource(id = R.drawable.ic_camera),
                    contentDescription = stringResource(id = R.string.desc_camera),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .width(64.dp)
                        .height(64.dp),
                )
            }

            Button(
                onClick = { /*TODO: 리뷰 작성*/ },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .padding(top = 35.dp, bottom = 18.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.action_add_review),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}