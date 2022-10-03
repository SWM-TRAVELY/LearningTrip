package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.notoSansKRFamily
import com.leeseungyun1020.learningtrip.viewmodel.HeritageViewModel

@Composable
fun HeritageScreen(navController: NavController, id: String) {
    val viewModel: HeritageViewModel = viewModel()
    val heritage by viewModel.heritage.observeAsState()

    var isDescriptionOpen by remember { mutableStateOf(false) }

    if (id.isDigitsOnly()) {
        viewModel.searchById(id.toInt())
    }
    LearningTripScaffold(
        title = stringResource(id = R.string.title_heritage_guide),
        setDisplayHomeAsUpEnabled = true,
        onHomeAsUpClicked = { navController.popBackStack() },
        setBodyContentInnerPadding = false,
    ) {
        Column(
            modifier = Modifier.verticalScroll(
                rememberScrollState()
            )
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(heritage?.imageURL)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.place_placeholder),
                    error = painterResource(R.drawable.place_placeholder),
                    fallback = painterResource(R.drawable.place_placeholder),
                    contentDescription = heritage?.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                )

                heritage?.let {
                    Box(
                        modifier = Modifier
                            .height(96.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
                            .background(Color.White)
                            .align(Alignment.BottomCenter)
                    ) {
                        Text(
                            text = heritage?.name ?: "",
                            fontSize = 20.sp,
                            fontFamily = notoSansKRFamily,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(horizontal = 30.dp)
                        )
                    }
                }
            }

            TopIndicatorTab(
                titles = listOf(
                    stringResource(id = R.string.description),
                    ""
                ), pages = listOf(
                    @Composable {
                        Column {
                            heritage?.let {
                                HeritageDescriptionPage(
                                    modifier = Modifier.padding(
                                        top = 10.dp,
                                        start = 32.dp,
                                        end = 32.dp
                                    ), heritage = it,
                                    isOpen = isDescriptionOpen,
                                    onOpenClicked = { isDescriptionOpen = !isDescriptionOpen }
                                )
                            }
                        }
                    },
                    @Composable {

                    }
                ), isMoveAble = listOf(true, false)
            )
        }
    }
}