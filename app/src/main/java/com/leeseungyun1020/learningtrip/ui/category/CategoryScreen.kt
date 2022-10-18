package com.leeseungyun1020.learningtrip.ui.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.Keyword
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.viewmodel.HomeViewModel

@Composable
fun CategoryScreen(navController: NavController) {
    val homeViewModel: HomeViewModel = viewModel()
    val categoryList by homeViewModel.recommendedKeywords.observeAsState()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        val keywordList = categoryList ?: listOf(
            Keyword(
                "신라",
                "https://img3.yna.co.kr/etc/inner/KR/2018/10/02/AKR20181002033500005_02_i_P4.jpg",

                ),
            Keyword(
                "백제",
                "https://www.heritage.go.kr/unisearch/images/national_treasure/thumb/2021102610465405.jpg",

                ),
            Keyword(
                "액티비티",
                "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=c9a2e1e6-d7ee-4969-aa50-96091dea4790",

                ),
            Keyword(
                "체험",
                "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=66941cc3-b6aa-4418-8d35-687eaab8b8c5",

                ),
        )
        items(count = keywordList.size) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color.Black)
                    .clickable(onClick = {
                        navController.navigate("${Screen.Search.root}/${keywordList[it].name}")
                    })
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(keywordList[it].imageURL)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_baseline_image_24),
                    contentDescription = keywordList[it].name,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Black),
                    alpha = 0.5f,
                )
                Text(
                    text = keywordList[it].name,
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                )
            }
        }

    }
}