package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.Place
import com.leeseungyun1020.learningtrip.model.PlaceReview
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme
import com.leeseungyun1020.learningtrip.ui.theme.Tertiary
import com.leeseungyun1020.learningtrip.ui.theme.notoSansKRFamily

@Composable
fun PlaceSummationBox(
    modifier: Modifier = Modifier,
    place: Place,
    placeReview: PlaceReview,
    onPlayClick: () -> Unit,
    onStickerClick: () -> Unit
) {

    Box(
        modifier = modifier
            .defaultMinSize(minHeight = 96.dp)
            .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp))
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 10.dp, end = 24.dp)
        ) {

            SmallTextButton(
                text = stringResource(id = R.string.action_play),
                icon = Icons.Default.PlayArrow,
                onClick = onPlayClick
            )

            SmallTextButton(
                text = stringResource(id = R.string.action_sticker),
                icon = painterResource(id = R.drawable.ic_sticker),
                onClick = onStickerClick
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 10.dp, start = 30.dp)
        ) {
            Text(
                text = place.name ?: "",
                fontSize = 20.sp,
                fontFamily = notoSansKRFamily,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(modifier = Modifier.padding(top = 2.dp)) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(id = R.string.star),
                    modifier = Modifier
                        .width(14.dp)
                        .height(14.dp),
                    tint = Tertiary
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = String.format("%.1f", placeReview.rating),
                    fontSize = 10.sp,
                    fontFamily = notoSansKRFamily
                )
                Spacer(modifier = Modifier.width(1.dp))
                Text(
                    text = "(${placeReview.review})",
                    fontSize = 10.sp,
                    fontFamily = notoSansKRFamily
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceSummationBoxPreview() {
    LearningTripTheme {
        PlaceSummationBox(
            modifier = Modifier.fillMaxWidth(),
            place = Place(
                id = 1,
                name = "서울역",
                typeId = 14,
                address = "서울시 중구 을지로동 서울역",
                chkInTextbook = true,
                latitude = 37.55,
                longitude = 126.98,
                tel = "051-000-0000",
                overview = "서울역은 서울시 중구 을지로동 서울역이다.",
                imageURL = "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d7036095-6472-4c84-8aee-335314640c34",
                restDate = "Rest Date",
                useTime = "Use Time",
                chkParking = false,
                chkBabyCarriage = false,
                chkPets = false,
                ageAvailable = "전연령",
                expGuide = "체험여행자료",
                chkWorldCultural = false,
                chkWorldNatural = false,
                chkWorldRecord = true,
            ),
            placeReview = PlaceReview(1, 4.88, 100),
            onPlayClick = {},
            onStickerClick = {},
        )
    }
}