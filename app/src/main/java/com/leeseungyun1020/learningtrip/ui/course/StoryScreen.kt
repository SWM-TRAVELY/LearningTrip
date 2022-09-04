package com.leeseungyun1020.learningtrip.ui.course

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.DetailedCourse
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.CourseBox
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold

@Composable
fun StoryScreen(navController: NavController) {
    LearningTripScaffold(
        title = stringResource(id = R.string.nav_story),
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("${Screen.AddCourse.root}/-1") }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.action_add)
                )
            }
        }) {
        val courseList = listOf(
            DetailedCourse(
                1, "코스1", listOf(
                    SimplePlace(
                        1,
                        "관광지1",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34"
                    ),
                    SimplePlace(
                        2,
                        "관광지2",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34"
                    ),
                )
            ),
            DetailedCourse(
                2, "코스2", listOf(
                    SimplePlace(
                        3,
                        "관광지3",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                    ),
                    SimplePlace(
                        4,
                        "관광지4",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                    ),
                    SimplePlace(
                        5,
                        "관광지5",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                    ),
                    SimplePlace(
                        6,
                        "관광지6",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                    ),
                )
            )
        )

        LazyColumn(modifier = Modifier.padding(top = 28.dp, start = 20.dp, end = 20.dp)) {
            items(courseList) { course ->
                CourseBox(detailedCourse = course, modifier = Modifier
                    .padding(bottom = 12.dp)
                    .clickable {
                        navController.navigate("${Screen.Course.root}/${course.id}")
                    })
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StoryScreenPrev() {
    StoryScreen(navController = rememberNavController())
}