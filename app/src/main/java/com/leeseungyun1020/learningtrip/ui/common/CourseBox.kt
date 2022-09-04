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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.DetailedCourse
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.ui.theme.notoSansKRFamily
import java.lang.Integer.min

@Composable
fun CourseBox(modifier: Modifier = Modifier, detailedCourse: DetailedCourse) {
    Box(modifier = modifier) {
        // TODO: 코스 이미지 URL로 교체
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(detailedCourse.placeList.first().imageURL)
                .crossfade(true)
                .build(),
            //TODO: Replace keyword placeholder
            placeholder = painterResource(R.drawable.ic_baseline_image_24),
            contentDescription = detailedCourse.name,
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
                text = detailedCourse.name,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                modifier = Modifier.padding(bottom = 1.dp)
            )
            Row {
                for (text in detailedCourse.placeList.slice(
                    0..min(
                        2,
                        detailedCourse.placeList.lastIndex
                    )
                )
                    .map { it.name })
                    Text(
                        text = text,
                        fontFamily = notoSansKRFamily,
                        fontSize = 9.sp,
                        color = Color.White,
                        modifier = Modifier.padding(end = 20.dp)
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseBoxPreview() {
    CourseBox(
        detailedCourse = DetailedCourse(
            2, "코스2", listOf(
                SimplePlace("3", "관광지3", "14", "주소", "image3"),
                SimplePlace("4", "관광지4", "14", "주소", "image4"),
                SimplePlace("5", "관광지5", "14", "주소", "image5"),
                SimplePlace("6", "관광지6", "14", "주소", "image6")
            )
        )
    )
}