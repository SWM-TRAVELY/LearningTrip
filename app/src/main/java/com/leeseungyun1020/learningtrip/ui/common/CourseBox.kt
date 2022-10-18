package com.leeseungyun1020.learningtrip.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.SimpleCourse
import com.leeseungyun1020.learningtrip.ui.theme.notoSansKRFamily

@Composable
fun CourseBox(modifier: Modifier = Modifier, course: SimpleCourse) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(course.imageURL)
                .crossfade(true)
                .build(),
            //TODO: Replace keyword placeholder
            placeholder = painterResource(R.drawable.ic_baseline_image_24),
            contentDescription = course.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(5.dp))
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color.Black.copy(alpha = 0f), Color.Black.copy(alpha = 0.8f))
                    )
                )
        ) {

        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 12.dp, bottom = 6.dp)
        ) {
            Text(
                text = course.name,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                modifier = Modifier.padding(bottom = 1.dp)
            )
            Row {
                val placeList = listOfNotNull(course.place1, course.place2, course.place3)
                for (text in placeList)
                    Text(
                        text = text,
                        fontFamily = notoSansKRFamily,
                        fontSize = 9.sp,
                        color = Color.White,
                        modifier = Modifier.padding(end = 20.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseBoxPreview() {
    CourseBox(
        course = SimpleCourse(
            id = 2, name = "코스2", imageURL = "image3",
            place1 = "관광지1", place2 = "관광지2", place3 = "관광지3"
        )
    )
}