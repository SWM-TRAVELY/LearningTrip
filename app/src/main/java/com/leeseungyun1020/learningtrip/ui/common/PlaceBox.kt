package com.leeseungyun1020.learningtrip.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.SimplePlace

@Composable
fun PlaceBox(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    simplePlace: SimplePlace
) {

    Column(modifier = modifier) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(simplePlace.imageURL)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.place_placeholder),
            error = painterResource(R.drawable.place_placeholder),
            fallback = painterResource(R.drawable.place_placeholder),
            contentDescription = simplePlace.name,
            contentScale = ContentScale.Crop,
            modifier = imageModifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
        )

        Text(text = simplePlace.name, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceBoxPreview() {
    PlaceBox(simplePlace = SimplePlace(1, "관광지1", "14", "image1"))
}