package com.leeseungyun1020.learningtrip.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.ui.common.PlaceBox

@Composable
fun PlaceListView(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    placeList: List<SimplePlace>,
    onPlaceClicked: (SimplePlace) -> Unit
) {
    Column(modifier = modifier) {
        for (i in 0..placeList.lastIndex step 2) {
            Row {
                PlaceBox(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                            bottom = innerPadding.calculateBottomPadding(),
                            top = if (i == 0) 0.dp else innerPadding.calculateTopPadding()
                        )
                        .clickable { onPlaceClicked(placeList[i]) },
                    imageModifier = Modifier
                        .height(136.dp),
                    simplePlace = placeList[i],
                )
                val place = placeList.getOrNull(i + 1)
                if (place != null) {
                    PlaceBox(
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                start = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                                bottom = innerPadding.calculateBottomPadding(),
                                top = if (i == 0) 0.dp else innerPadding.calculateTopPadding()
                            )
                            .clickable { onPlaceClicked(place) },
                        imageModifier = Modifier
                            .height(136.dp),
                        simplePlace = place,
                    )
                } else {
                    Spacer(Modifier.weight(1f))
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceListViewPreview() {
    PlaceListView(
        innerPadding = PaddingValues(top = 10.dp, start = 4.dp, end = 4.dp),
        placeList = listOf(
            SimplePlace(
                "1",
                "관광지1",
                "14",
                "주소",
                "http://tong.visitkorea.or.kr/cms/resource/47/2678647_image2_1.jpg"
            ),
            SimplePlace(
                "2",
                "관광지2",
                "14",
                "주소",
                "http://tong.visitkorea.or.kr/cms/resource/47/2678647_image2_1.jpg"
            ),
            SimplePlace(
                "3",
                "관광지3",
                "14",
                "주소",
                "http://tong.visitkorea.or.kr/cms/resource/47/2678647_image2_1.jpg"
            ),
            SimplePlace(
                "4",
                "관광지4",
                "14",
                "주소",
                "http://tong.visitkorea.or.kr/cms/resource/47/2678647_image2_1.jpg"
            ),
        ),
        onPlaceClicked = {}
    )
}