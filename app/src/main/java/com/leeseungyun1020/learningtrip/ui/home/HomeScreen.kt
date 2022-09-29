package com.leeseungyun1020.learningtrip.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.Keyword
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.CourseBox
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.Gray2
import com.leeseungyun1020.learningtrip.ui.theme.Gray3
import com.leeseungyun1020.learningtrip.viewmodel.HomeViewModel
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(navController: NavController, placeViewModel: PlaceViewModel) {
    val homeViewModel: HomeViewModel = viewModel()

    val recommendedKeywords by homeViewModel.recommendedKeywords.observeAsState()
    val mainBanners by homeViewModel.mainBanners.observeAsState()
    val recommendedCourses by homeViewModel.recommendedCourses.observeAsState()

    val searchedPlaceNames by placeViewModel.filteredPlaceNames.observeAsState()
    val recommendedPlaces by placeViewModel.recommendedPlaces.observeAsState()
    var searchText by rememberSaveable { mutableStateOf("") }

    placeViewModel.recommend()

    LearningTripScaffold(
        title = stringResource(id = R.string.app_name),
        topBarExtraContent = {
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    placeViewModel.placeNameByKeyword(it)
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        navController.navigate("${Screen.Search.root}/$searchText")
                    },
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .padding(
                        top = 8.dp
                    )
                    .fillMaxWidth(),
                placeholder = {
                    Row {
                        Icon(Icons.Filled.Search, stringResource(id = R.string.action_search))
                        Spacer(modifier = Modifier.padding(start = 4.dp))
                        Text(
                            text = stringResource(id = R.string.hint_search),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                },
                singleLine = true,
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        IconButton(onClick = { searchText = "" }) {
                            Icon(
                                Icons.Filled.Clear,
                                stringResource(id = R.string.action_delete)
                            )
                        }
                    }
                },

                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White,
                    placeholderColor = Gray3
                ),
                shape = RoundedCornerShape(10.dp)
            )
        }, bodyContent = {
            if (searchText.isEmpty()) {
                Column(
                    modifier = Modifier.verticalScroll(
                        rememberScrollState()
                    )
                ) {
                    // Keyword List
                    val keywordList = recommendedKeywords ?: listOf(
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
                    if (keywordList.isNotEmpty()) {
                        Text(
                            modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                            text = stringResource(id = R.string.title_recommend_keyword),
                            color = Gray2, style = MaterialTheme.typography.bodyLarge,
                        )
                        KeywordListView(
                            modifier = Modifier.padding(top = 8.dp),
                            innerStartPadding = 16.dp,
                            keywordList = keywordList,
                        ) {
                            navController.navigate("${Screen.Search.root}/${it.name}")
                        }
                    }

                    // Introduce(Banner) Image
                    if ((mainBanners?.size ?: 0) > 0) {
                        HorizontalPager(
                            count = mainBanners?.size ?: 0,
                            modifier = Modifier
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                .height(104.dp)
                        ) { page ->
                            val banner = mainBanners?.getOrNull(page)
                            if (banner != null) {
                                AsyncImage(
                                    model = banner.imageURL,
                                    contentDescription = stringResource(id = R.string.desc_banner),
                                    modifier = Modifier.height(104.dp),
                                    contentScale = ContentScale.FillWidth,
                                )
                            } else {
                                Text(
                                    text = "Learning Trip - Page: $page",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }

                    // Place List
                    Text(
                        modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                        text = stringResource(id = R.string.title_recommend_place),
                        color = Gray2, style = MaterialTheme.typography.bodyLarge,
                    )
                    PlaceListView(
                        modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                        innerPadding = PaddingValues(top = 10.dp, start = 4.dp, end = 4.dp),
                        placeList = recommendedPlaces ?: listOf(),
                    ) {
                        navController.navigate("${Screen.Place.root}/${it.id}")
                    }

                    // Course List
                    val courseList = recommendedCourses ?: emptyList()
                    if (courseList.isNotEmpty()) {
                        Text(
                            modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                            text = stringResource(id = R.string.title_recommend_course),
                            color = Gray2, style = MaterialTheme.typography.bodyLarge,
                        )

                        val startIndex = Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2 % courseList.size)
                        HorizontalPager(
                            count = Int.MAX_VALUE,
                            contentPadding = PaddingValues(horizontal = 24.dp),
                            itemSpacing = 8.dp,
                            state = rememberPagerState(initialPage = startIndex),
                        ) { idx ->
                            val page = idx % courseList.size
                            CourseBox(course = courseList[page], modifier = Modifier.clickable {
                                navController.navigate("${Screen.Course.root}/${courseList[page].id}")
                            })
                        }
                    }
                }

            } else {
                BackHandler {
                    searchText = ""
                }

                TextListView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textList = searchedPlaceNames ?: listOf(),
                    onTextClicked = { keyword -> navController.navigate("${Screen.Search.root}/$keyword") }
                )
            }

        })
}