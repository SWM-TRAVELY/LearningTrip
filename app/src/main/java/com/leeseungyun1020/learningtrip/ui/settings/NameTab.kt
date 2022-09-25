package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.theme.Gray3

@Composable
fun NameTab(
    name: String,
    email: String,
    imageURL: String,
    onMoveClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clickable { onMoveClicked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageURL)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_baseline_person_24),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .width(46.dp)
                .height(46.dp),
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f)
        ) {
            Row {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                LevelBadge(
                    label = "장인 탐험가",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            Text(text = email, style = MaterialTheme.typography.labelSmall.copy(color = Gray3))
        }

            Icon(
                imageVector = Icons.Default.NavigateNext,
                contentDescription = stringResource(id = R.string.action_detail)
            )
    }
}

@Preview(showBackground = true)
@Composable
fun NameTabPrev() {
    NameTab(
        name = "이승윤",
        email = "example@exmaple.com",
        imageURL = "https://avatars.githubusercontent.com/u/34941061",
        onMoveClicked = {},
        modifier = Modifier.fillMaxWidth()
    )
}