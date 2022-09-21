package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.learningtrip.R

@Composable
fun SettingIconButton(painter: Painter, title: String, onClick: () -> Unit) {
    Box(modifier = Modifier
        .width(90.dp)
        .height(80.dp)
        .clickable { onClick() }) {
        Image(
            painter = painter,
            contentDescription = title,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingIconButtonPrev() {
    SettingIconButton(
        painter = painterResource(id = R.drawable.ic_review),
        title = "Reviews",
        onClick = {}
    )
}