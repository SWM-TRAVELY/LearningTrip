package com.leeseungyun1020.learningtrip.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.ui.common.CourseBox

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
            .clickable { centerIndex-- })

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
            .clickable { centerIndex++ })
    }
}

@Preview(showBackground = true)
@Composable
fun CourseListViewPreview() {
    CourseListView(
        modifier = Modifier
            .padding(vertical = 8.dp),
        courseList = listOf(
            Course(
                "1", "코스1", listOf(
                    SimplePlace(
                        "1",
                        "관광지1",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34"
                    ),
                    SimplePlace(
                        "2",
                        "관광지2",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34"
                    ),
                )
            ),
            Course(
                "2", "코스2", listOf(
                    SimplePlace(
                        "3",
                        "관광지3",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                    ),
                    SimplePlace(
                        "4",
                        "관광지4",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                    ),
                    SimplePlace(
                        "5",
                        "관광지5",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                    ),
                    SimplePlace(
                        "6",
                        "관광지6",
                        "14",
                        "주소",
                        "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=5dc8cd08-f083-4595-b28d-ad3dbae7bd54"
                    ),
                )
            )
        ),
        onCourseClicked = {}
    )
}