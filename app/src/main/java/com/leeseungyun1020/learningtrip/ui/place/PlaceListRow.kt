package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.ui.common.PlaceBox

@Composable
fun PlaceListRow(
    modifier: Modifier = Modifier,
    title: String,
    placeStartPadding: Dp,
    places: List<SimplePlace>,
    onPlaceClick: (SimplePlace) -> Unit
) {
    Column {
        Text(
            text = title,
            modifier = modifier,
            style = MaterialTheme.typography.bodyLarge
        )

        LazyRow(modifier = Modifier.padding(top = 10.dp)) {
            items(count = places.size, key = { places[it].id }) {
                val modifier = if (it == 0)
                    Modifier.padding(start = placeStartPadding)
                else Modifier
                PlaceBox(
                    modifier = modifier
                        .padding(end = 8.dp)
                        .clickable { onPlaceClick(places[it]) },
                    imageModifier = Modifier
                        .width(160.dp)
                        .height(150.dp),
                    simplePlace = places[it]
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceListRowPreview() {
    PlaceListRow(modifier = Modifier.padding(top = 16.dp, start = 16.dp),
        title = "Title",
        places = listOf(
            SimplePlace(
                id = 1,
                typeId = 14,
                name = "장소1",
                address = "주소",
                imageURL = "https://upload.wikimedia.org/wikipedia/commons/3/32/%EA%B2%BD%EC%A3%BC_%EB%B6%88%EA%B5%AD%EC%82%AC.jpg"
            ),
            SimplePlace(
                id = 2,
                typeId = 14,
                name = "장소2",
                address = "주소",
                imageURL = "https://upload.wikimedia.org/wikipedia/commons/3/32/%EA%B2%BD%EC%A3%BC_%EB%B6%88%EA%B5%AD%EC%82%AC.jpg"
            ),
            SimplePlace(
                id = 3,
                typeId = 14,
                name = "장소3",
                address = "주소",
                imageURL = "https://upload.wikimedia.org/wikipedia/commons/3/32/%EA%B2%BD%EC%A3%BC_%EB%B6%88%EA%B5%AD%EC%82%AC.jpg"
            ),
        ),
        placeStartPadding = 16.dp,
        onPlaceClick = {}
    )
}