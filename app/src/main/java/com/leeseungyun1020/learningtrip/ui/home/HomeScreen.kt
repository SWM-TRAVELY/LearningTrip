package com.leeseungyun1020.learningtrip.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.Screen
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.Keyword
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.ui.common.CourseBox
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.common.PlaceBox
import com.leeseungyun1020.learningtrip.ui.theme.Gray2
import com.leeseungyun1020.learningtrip.ui.theme.Gray3
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme

@Composable
fun HomeScreen(navController: NavController) {
    var searchText by rememberSaveable { mutableStateOf("") }

    LearningTripScaffold(
        title = stringResource(id = R.string.app_name),
        topBarExtraContent = {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
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
            // Keyword List
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                text = stringResource(id = R.string.title_recommend_keyword),
                color = Gray2, fontSize = 16.sp
            )
            KeywordListView(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                listOf(
                    Keyword("", "키워드1"),
                    Keyword("", "키워드2"),
                    Keyword("", "키워드3"),
                    Keyword("", "키워드4"),
                    Keyword("", "키워드5"),
                    Keyword("", "키워드6"),
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
                listOf(
                    SimplePlace("1", "관광지1", "14", "image1"),
                    SimplePlace("2", "관광지2", "14", "image2"),
                    SimplePlace("3", "관광지3", "14", "image3"),
                    SimplePlace("4", "관광지4", "14", "image4"),
                )
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
                            SimplePlace("1", "관광지1", "14", "image1"),
                            SimplePlace("2", "관광지2", "14", "image2"),
                        )
                    ),
                    Course(
                        "2", "코스2", listOf(
                            SimplePlace("3", "관광지3", "14", "image3"),
                            SimplePlace("4", "관광지4", "14", "image4"),
                            SimplePlace("5", "관광지5", "14", "image5"),
                            SimplePlace("6", "관광지6", "14", "image6"),
                        )
                    )
                )
            ) {
                navController.navigate("${Screen.Course.root}/${it.id}")
            }
        })
}

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
                // Keyword Image
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

@Composable
fun BannerView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
fun PlaceListView(
    modifier: Modifier = Modifier,
    placeList: List<SimplePlace>,
    onPlaceClicked: (SimplePlace) -> Unit
) {
    Column(modifier = modifier) {
        for (i in 0..placeList.lastIndex step 2) {
            Row {
                for (place in listOf(placeList[i], placeList.getOrNull(i + 1) ?: placeList.last()))
                    PlaceBox(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onPlaceClicked(place) },
                        simplePlace = place
                    )
            }
        }
    }
}


@Composable
fun CourseListView(
    modifier: Modifier,
    courseList: List<Course>,
    onCourseClicked: (Course) -> Unit
) {
    var lastTime by remember { mutableStateOf(System.currentTimeMillis()) }
    var centerIndex by remember { mutableStateOf(0) }

    centerIndex = (centerIndex + courseList.size) % courseList.size
    val startRoute = when (centerIndex) {
        0 -> courseList.last()
        else -> courseList[centerIndex - 1]
    }
    val centerRoute = courseList[centerIndex]
    val endRoute = when (centerIndex) {
        courseList.lastIndex -> courseList.first()
        else -> courseList[centerIndex + 1]
    }

    ConstraintLayout(modifier = modifier
        .scrollable(
            orientation = Orientation.Horizontal,
            state = rememberScrollableState { delta ->
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastTime > 1000) {
                    lastTime = currentTime
                    if (delta > 1) {
                        centerIndex--
                    }
                    if (delta < -1) {
                        centerIndex++
                    }
                }
                delta
            }
        )
    ) {
        val (startBox, centerBox, endBox) = createRefs()
        CourseBox(course = startRoute, modifier = Modifier
            .constrainAs(startBox) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(centerBox.start)
            }
            .clickable { onCourseClicked(startRoute) })

        CourseBox(course = centerRoute, modifier = Modifier
            .constrainAs(centerBox) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .width(310.dp)
            .padding(horizontal = 8.dp)
            .clickable { onCourseClicked(centerRoute) }
        )

        CourseBox(course = endRoute, modifier = Modifier
            .constrainAs(endBox) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(centerBox.end)
            }
            .clickable { onCourseClicked(endRoute) })
    }
//    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LearningTripTheme {
        HomeScreen(navController = rememberNavController())
    }
}