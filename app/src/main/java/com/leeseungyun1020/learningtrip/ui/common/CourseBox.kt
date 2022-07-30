package com.leeseungyun1020.learningtrip.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.ui.theme.Gray2
import java.lang.Integer.min

@Composable
fun CourseBox(modifier: Modifier = Modifier, course: Course) {
    Box(modifier = modifier) {
        // TODO: 코스 이미지 URL로 교체
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_image_24),
            contentDescription = course.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Gray2)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 12.dp, bottom = 6.dp)
        ) {
            Text(
                text = course.name,
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 1.dp)
            )
            Row {
                for (text in course.placeList.slice(0..min(2, course.placeList.lastIndex))
                    .map { it.name })
                    Text(
                        text = text,
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
        course = Course(
            "2", "코스2", listOf(
                SimplePlace("3", "관광지3", "14", "image3"),
                SimplePlace("4", "관광지4", "14", "image4"),
                SimplePlace("5", "관광지5", "14", "image5"),
                SimplePlace("6", "관광지6", "14", "image6")
            )
        )
    )
}