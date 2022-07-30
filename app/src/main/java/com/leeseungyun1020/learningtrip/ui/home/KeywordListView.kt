package com.leeseungyun1020.learningtrip.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.leeseungyun1020.learningtrip.model.Keyword

@Composable
fun KeywordListView(
    modifier: Modifier = Modifier,
    keywordList: List<Keyword>,
    onKeywordClicked: (Keyword) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(count = keywordList.size) {
            Box(
                modifier = Modifier
                    .width(86.dp)
                    .height(86.dp)
                    .clip(CircleShape)
                    .background(color = Color.Black)
                    .clickable(onClick = { onKeywordClicked(keywordList[it]) })
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(keywordList[it].imageURL)
                        .crossfade(true)
                        .build(),
                    //TODO: Change keyword placeholder
                    placeholder = painterResource(R.drawable.ic_baseline_image_24),
                    contentDescription = keywordList[it].name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
                Text(
                    text = keywordList[it].name,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 12.sp,
                    color = Color.White,
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun KeywordListViewPreview() {
    KeywordListView(
        keywordList = listOf(
            Keyword(
                "https://img3.yna.co.kr/etc/inner/KR/2018/10/02/AKR20181002033500005_02_i_P4.jpg",
                "키워드1"
            ),
            Keyword(
                "https://img3.yna.co.kr/etc/inner/KR/2018/10/02/AKR20181002033500005_02_i_P4.jpg",
                "키워드2"
            ),
            Keyword(
                "https://img3.yna.co.kr/etc/inner/KR/2018/10/02/AKR20181002033500005_02_i_P4.jpg",
                "키워드3"
            ),
            Keyword(
                "https://img3.yna.co.kr/etc/inner/KR/2018/10/02/AKR20181002033500005_02_i_P4.jpg",
                "키워드4"
            ),
            Keyword(
                "https://img3.yna.co.kr/etc/inner/KR/2018/10/02/AKR20181002033500005_02_i_P4.jpg",
                "키워드5"
            ),
            Keyword(
                "https://img3.yna.co.kr/etc/inner/KR/2018/10/02/AKR20181002033500005_02_i_P4.jpg",
                "키워드6"
            ),
        ),
        onKeywordClicked = {}
    )
}