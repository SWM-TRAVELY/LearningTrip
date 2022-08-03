package com.leeseungyun1020.learningtrip.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.Keyword
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.placeViewModel
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.Gray2
import com.leeseungyun1020.learningtrip.ui.theme.Gray3
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme

@Composable
fun HomeScreen(navController: NavController) {
    val searchedPlaceNames by placeViewModel.filteredPlaceNames.observeAsState()
    val recommendedPlaces by placeViewModel.recommendedPlaces.observeAsState()
    var searchText by rememberSaveable { mutableStateOf("") }

    LearningTripScaffold(
        title = stringResource(id = R.string.app_name),
        topBarExtraContent = {
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    placeViewModel.placeNameByKeyword(it)
                },
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
                        Text(text = stringResource(id = R.string.hint_search))
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
                // Keyword List
                Text(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                    text = stringResource(id = R.string.title_recommend_keyword),
                    color = Gray2, fontSize = 16.sp
                )
                KeywordListView(
                    modifier = Modifier.padding(top = 8.dp),
                    innerStartPadding = 16.dp,
                    keywordList = listOf(
                        Keyword(
                            "https://img3.yna.co.kr/etc/inner/KR/2018/10/02/AKR20181002033500005_02_i_P4.jpg",
                            "키워드1"
                        ),
                        Keyword(
                            "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=1f9b44e2-bd54-42a6-b4ab-287eca75e023",
                            "키워드2"
                        ),
                        Keyword(
                            "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=c9a2e1e6-d7ee-4969-aa50-96091dea4790",
                            "키워드3"
                        ),
                        Keyword(
                            "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=66941cc3-b6aa-4418-8d35-687eaab8b8c5",
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
                    )
                ) {
                    navController.navigate("${Screen.Search.root}/${it.name}")
                }

                // Introduce(Banner) Image
                BannerView(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .height(104.dp)
                        .background(color = Gray3)
                )

                // Place List
                Text(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                    text = stringResource(id = R.string.title_recommend_place),
                    fontSize = 16.sp, color = Gray2
                )
                PlaceListView(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    innerPadding = PaddingValues(top = 10.dp, start = 4.dp, end = 4.dp),
                    placeList = recommendedPlaces ?: listOf(),
                ) {
                    navController.navigate("${Screen.Place.root}/${it.id}")
                }

                // Route List
                Text(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                    text = stringResource(id = R.string.title_recommend_course),
                    fontSize = 16.sp, color = Gray2
                )

                CourseListView(
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    courseList = listOf(
                        Course(
                            "1", "코스1", listOf(
                                SimplePlace(
                                    1,
                                    "관광지1",
                                    "14",
                                    "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34"
                                ),
                                SimplePlace(
                                    2,
                                    "관광지2",
                                    "14",
                                    "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34"
                                ),
                            )
                        ),
                        Course(
                            "2", "코스2", listOf(
                                SimplePlace(
                                    3,
                                    "관광지3",
                                    "14",
                                    "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                                ),
                                SimplePlace(
                                    4,
                                    "관광지4",
                                    "14",
                                    "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                                ),
                                SimplePlace(
                                    5,
                                    "관광지5",
                                    "14",
                                    "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                                ),
                                SimplePlace(
                                    6,
                                    "관광지6",
                                    "14",
                                    "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                                ),
                            )
                        )
                    )
                ) {
                    navController.navigate("${Screen.Course.root}/${it.id}")
                }
            } else {
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LearningTripTheme {
        HomeScreen(navController = rememberNavController())
    }
}