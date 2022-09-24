package com.leeseungyun1020.learningtrip.ui.settings

import android.content.res.ColorStateList
import android.widget.RatingBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.SimplePlaceReview

@Composable
fun WrittenReviewTab(
    simplePlaceReview: SimplePlaceReview,
    onDeleteReviewButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(simplePlaceReview.placeImageURL)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.place_placeholder),
                error = painterResource(id = R.drawable.place_placeholder),
                contentDescription = simplePlaceReview.placeName,
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
                Text(text = simplePlaceReview.placeName, style = MaterialTheme.typography.bodyLarge)
                val tertiary = MaterialTheme.colorScheme.tertiary
                AndroidView(
                    factory = {
                        RatingBar(it, null, android.R.attr.ratingBarStyleSmall)
                    },
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    it.rating = simplePlaceReview.rating.toFloat()
                    it.numStars = 5
                    it.setIsIndicator(true)
                    it.progressTintList = ColorStateList.valueOf(tertiary.toArgb())
                }

            }

            TextButton(
                onClick = { onDeleteReviewButtonClick() },
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(vertical = 6.dp, horizontal = 12.dp)
            ) {
                Text(text = stringResource(id = R.string.action_delete_review))
            }
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(simplePlaceReview.reviewImageURL)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.place_placeholder),
            error = painterResource(id = R.drawable.place_placeholder),
            contentDescription = simplePlaceReview.placeName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 14.dp)
                .height(180.dp),
        )

        Text(
            text = simplePlaceReview.content, style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 14.dp)
        )
    }

}

@Preview
@Composable
fun WrittenReviewTabPrev() {
    WrittenReviewTab(
        simplePlaceReview = SimplePlaceReview(
            3,
            "imageURL",
            "placeName",
            4.1,
            "imageURL",
            "reviewContent"
        ), onDeleteReviewButtonClick = { })
}