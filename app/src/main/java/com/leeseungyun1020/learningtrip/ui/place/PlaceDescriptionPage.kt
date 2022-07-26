package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            text = stringResource(id = R.string.title_simple_desc),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = place.overview ?: "", style = MaterialTheme.typography.bodySmall,
            color = Gray2,
            maxLines = if (!isOpen) 3 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis
        )

        if (isOpen) {
            // Policy
            val parkingText =
                if (place.chkParking == true)
                    stringResource(id = R.string.title_available)
                else
                    stringResource(id = R.string.title_not_available)
            val otherText = "${stringResource(id = R.string.title_pet)} ${
                if (place.chkPets == true)
                    stringResource(id = R.string.title_available)
                else
                    stringResource(id = R.string.title_not_available)
            }, ${stringResource(id = R.string.title_baby_carriage)} ${
                if (place.chkBabyCarriage == true)
                    stringResource(id = R.string.title_available)
                else
                    stringResource(id = R.string.title_not_available)
            }"
            CelledTextListView(
                modifier = Modifier.padding(top = 10.dp),
                title = stringResource(id = R.string.title_policy),
                items = listOf(
                    stringResource(id = R.string.title_use_time) to (place.useTime ?: ""),
                    stringResource(id = R.string.title_rest_date) to (place.restDate ?: ""),
                    stringResource(id = R.string.title_telephone) to (place.tel ?: ""),
                    stringResource(id = R.string.title_parking) to parkingText,
                    stringResource(id = R.string.title_other) to otherText,
                ),
                fontColor = Gray2,
                showDialog = true
            )

            // Experience Guide
            CelledTextListView(
                modifier = Modifier.padding(top = 10.dp),
                title = stringResource(id = R.string.title_exprience_guide),
                items = listOf(
                    stringResource(id = R.string.title_recommended_age) to (place.ageAvailable
                        ?: ""),
                    stringResource(id = R.string.title_exprience_guide) to (place.expGuide ?: ""),
                ),
                fontColor = Gray2,
                showDialog = true
            )

            // Heritage Guide
            if (place.chkWorldCultural == true || place.chkWorldNatural == true || place.chkWorldRecord == true || place.chkInTextbook == true)
                Text(
                    modifier = Modifier.padding(top = 10.dp, bottom = 2.dp),
                    text = stringResource(id = R.string.title_heritage_guide),
                    style = MaterialTheme.typography.bodyMedium,
                )
            if (place.chkWorldCultural == true)
                Text(
                    text = "• ${stringResource(id = R.string.title_world_cultural)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray2
                )
            if (place.chkWorldNatural == true)
                Text(
                    text = "• ${stringResource(id = R.string.title_world_natural)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray2
                )
            if (place.chkWorldRecord == true)
                Text(
                    text = "• ${stringResource(id = R.string.title_world_record)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray2
                )
            if (place.chkInTextbook == true)
                Text(
                    text = "• ${stringResource(id = R.string.title_textbook)}",
                    style = MaterialTheme.typography.bodySmall,
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