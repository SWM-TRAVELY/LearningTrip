package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.leeseungyun1020.learningtrip.ui.theme.Gray3

@Composable
fun EditableReviewTab(
    simplePlace: SimplePlace,
    onReviewButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
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
                .width(50.dp)
                .height(50.dp),
        )

        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f)
        ) {
            Text(text = simplePlace.name ?: "", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = simplePlace.address ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = Gray3,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Button(
            onClick = { onReviewButtonClick() },
            shape = RoundedCornerShape(5.dp),
            contentPadding = PaddingValues(vertical = 6.dp, horizontal = 12.dp)
        ) {
            Text(text = stringResource(id = R.string.action_write_review))
        }
    }
}

@Preview
@Composable
fun EditableReviewTabPrev() {
    EditableReviewTab(
        simplePlace = SimplePlace(1, "name", "14", "address", "imageURL"),
        onReviewButtonClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
    )
}