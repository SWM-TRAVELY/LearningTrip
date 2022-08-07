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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningntripapitest.SimpleHeritage
import com.leeseungyun1020.learningtrip.R

//괴도 성민 왔다감
@Composable
fun HeritageBox(modifier: Modifier = Modifier, simpleHeritage: SimpleHeritage) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(simpleHeritage.imageURL)
                .crossfade(true)
                .build(),
            //TODO: Chnage heritage placeholder
            placeholder = painterResource(R.drawable.ic_baseline_image_24),
            contentDescription = simpleHeritage.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(35.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color.Black.copy(alpha = 0f), Color.Black.copy(alpha = 0.8f))
                    )
                )
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
            text = simpleHeritage.name,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeritageBoxPreview() {
    HeritageBox(
        modifier = Modifier
            .width(120.dp)
            .height(150.dp),
        simpleHeritage = SimpleHeritage(
            id = 5,
            name = "문화재1",
            imageURL = "https://upload.wikimedia.org/wikipedia/commons/e/e2/Cheomseongdae-1.jpg"
        )
    )
}