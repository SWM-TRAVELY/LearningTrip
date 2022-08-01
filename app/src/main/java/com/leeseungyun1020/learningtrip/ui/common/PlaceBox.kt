package com.leeseungyun1020.learningtrip.ui.common

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.SimplePlace

//괴도 성민 왔다감
@Composable
fun PlaceBox(modifier: Modifier = Modifier, simplePlace: SimplePlace) {
    Log.e("LSY", "PlaceBox: $simplePlace")
    Column(modifier = modifier) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(simplePlace.imageURL)
                .crossfade(true)
                .build(),
            //TODO: Chnage place placeholder
            placeholder = painterResource(R.drawable.ic_baseline_image_24),
            contentDescription = simplePlace.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(136.dp)
                .clip(RoundedCornerShape(5.dp))
        )
//        Image(
//            painter = painterResource(id = R.drawable.ic_baseline_image_24),
//            contentDescription = "image",
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(136.dp)
//                .clip(RoundedCornerShape(5.dp))
//        )
        Text(text = simplePlace.name, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceBoxPreview() {
    PlaceBox(simplePlace = SimplePlace(1, "관광지1", "14", "image1"))
}