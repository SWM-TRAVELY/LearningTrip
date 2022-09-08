package com.leeseungyun1020.learningtrip.ui.course

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.SimplePlace

@Composable
fun PlaceLocationBox(simplePlace: SimplePlace, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Column(
            modifier = Modifier.height(90.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = stringResource(id = R.string.desc_place),
                tint = MaterialTheme.colorScheme.tertiary,
            )
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(3f, 2f), 0f)
            Canvas(
                Modifier
                    .fillMaxHeight()
                    .width(2.dp)
            ) {
                drawLine(
                    color = Color.Black,//MaterialTheme.colorScheme.secondary,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    pathEffect = pathEffect
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 4.dp)
                .weight(1f)
        ) {
            Text(text = simplePlace.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = simplePlace.address, style = MaterialTheme.typography.bodySmall)
        }
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
            modifier = Modifier
                .width(90.dp)
                .height(90.dp)
                .clip(RoundedCornerShape(5.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceLocationBoxPrev() {
    PlaceLocationBox(
        SimplePlace(
            1,
            "관광지1",
            "14",
            "주소 주소 주소 주소 주소 주소 주소 주소 주소 주소 주소 주소 주소",
            "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34"
        )
    )
}