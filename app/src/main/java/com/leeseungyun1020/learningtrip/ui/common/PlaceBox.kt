package com.leeseungyun1020.learningtrip.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.SimplePlace

@Composable
fun PlaceBox(modifier: Modifier = Modifier, simplePlace: SimplePlace) {
    Column(modifier = modifier) {
        // TODO: simplePlace의 이미지 URL로 교체
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_image_24),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxWidth()
                .height(136.dp)
                .clip(RoundedCornerShape(5.dp))
        )
        Text(text = simplePlace.name, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceBoxPreview() {
    PlaceBox(simplePlace = SimplePlace("1", "관광지1", "14", "image1"))
}