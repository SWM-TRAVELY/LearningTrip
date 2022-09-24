package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.learningtrip.ui.theme.Gray4

@Composable
fun RatingButton(
    rating: Int,
    onRatingClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    starSize: Dp = 24.dp
) {
    Row(modifier = modifier) {
        repeat(5) {
            IconButton(onClick = { onRatingClick(it + 1) }) {
                Icon(
                    imageVector = Icons.Default.Star, contentDescription = "${it + 1}",
                    tint = if (rating >= it + 1) MaterialTheme.colorScheme.tertiary else Gray4,
                    modifier = Modifier
                        .height(starSize)
                        .width(starSize),
                )
            }
        }
    }
}

@Preview
@Composable
fun RatingButtonPrev() {
    RatingButton(3, {})
}