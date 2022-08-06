package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.model.Place
import com.leeseungyun1020.learningtrip.ui.theme.Gray2
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme

@Composable
fun PlaceDescriptionPage(
    modifier: Modifier = Modifier,
    place: Place,
    isOpen: Boolean = false,
    onOpenClicked: () -> Unit = {}
) {
    Column(modifier = modifier) {
        // Simple Description
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = stringResource(id = R.string.title_simple_desc), fontSize = 14.sp
        )
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = place.overview, fontSize = 12.sp,
            color = Gray2,
            maxLines = if (!isOpen) 3 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis
        )

        if (isOpen) {
            // Policy
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = stringResource(id = R.string.title_policy), fontSize = 14.sp
            )
            val parkingText =
                if (place.chkParking)
                    stringResource(id = R.string.title_available)
                else
                    stringResource(id = R.string.title_not_available)
            val otherText = "${stringResource(id = R.string.title_pet)} ${
                if (place.chkPets)
                    stringResource(id = R.string.title_available)
                else
                    stringResource(id = R.string.title_not_available)
            }, ${stringResource(id = R.string.title_baby_carriage)} ${
                if (place.chkBabyCarriage)
                    stringResource(id = R.string.title_available)
                else
                    stringResource(id = R.string.title_not_available)
            }"
            CelledTextListView(
                modifier = Modifier.padding(top = 2.dp),
                items = listOf(
                    stringResource(id = R.string.title_use_time) to place.useTime,
                    stringResource(id = R.string.title_rest_date) to place.restDate,
                    stringResource(id = R.string.title_telephone) to place.tel,
                    stringResource(id = R.string.title_parking) to parkingText,
                    stringResource(id = R.string.title_other) to otherText,
                ),
                fontColor = Gray2
            )

            // Experience Guide
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = stringResource(id = R.string.title_exprience_guide), fontSize = 14.sp
            )
            CelledTextListView(
                modifier = Modifier.padding(top = 2.dp),
                items = listOf(
                    stringResource(id = R.string.title_recommended_age) to place.ageAvailable,
                    stringResource(id = R.string.title_exprience_guide) to place.expGuide,
                ),
                fontColor = Gray2
            )

            // Heritage Guide
            if (place.chkWorldCultural || place.chkWorldNatural || place.chkWorldRecord || place.chkInTextbook)
                Text(
                    modifier = Modifier.padding(top = 10.dp, bottom = 2.dp),
                    text = stringResource(id = R.string.title_heritage_guide), fontSize = 14.sp
                )
            if (place.chkWorldCultural)
                Text(
                    text = "• ${stringResource(id = R.string.title_world_cultural)}",
                    fontSize = 12.sp,
                    color = Gray2
                )
            if (place.chkWorldNatural)
                Text(
                    text = "• ${stringResource(id = R.string.title_world_natural)}",
                    fontSize = 12.sp,
                    color = Gray2
                )
            if (place.chkWorldRecord)
                Text(
                    text = "• ${stringResource(id = R.string.title_world_record)}",
                    fontSize = 12.sp,
                    color = Gray2
                )
            if (place.chkInTextbook)
                Text(
                    text = "• ${stringResource(id = R.string.title_textbook)}",
                    fontSize = 12.sp,
                    color = Gray2
                )
        }

        IconButton(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            onClick = onOpenClicked
        ) {
            if (isOpen)
                Icon(
                    imageVector = Icons.Default.ExpandLess,
                    contentDescription = stringResource(id = R.string.action_expand_less)
                )
            else
                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = stringResource(id = R.string.action_expand_more)
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceDescriptionPagePreview() {
    LearningTripTheme {
        PlaceDescriptionPage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            Place(
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
                restDate = "매주 월요일",
                useTime = "06:00 ~ 18:00",
                chkParking = false,
                chkBabyCarriage = false,
                chkPets = false,
                ageAvailable = "전연령",
                expGuide = "체험여행자료",
                chkWorldCultural = false,
                chkWorldNatural = false,
                chkWorldRecord = true,
            ),
            true
        )
    }
}