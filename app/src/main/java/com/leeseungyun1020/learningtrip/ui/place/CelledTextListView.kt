package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CelledTextListView(
    modifier: Modifier,
    items: List<Pair<String, String>>,
    fontColor: Color = Color.Black
) {
    Row(modifier = Modifier.padding(top = 2.dp)) {
        Column {
            for (item in items)
                Text(
                    text = item.first, fontSize = 12.sp, color = fontColor
                )
        }
        Column(modifier = Modifier.padding(start = 8.dp)) {
            for (i in 0..items.lastIndex)
                Text(
                    text = "|", fontSize = 12.sp, color = fontColor
                )
        }
        Column(modifier = Modifier.padding(start = 8.dp)) {
            for (item in items)
                Text(
                    text = item.second, fontSize = 12.sp, lineHeight = 12.sp, color = fontColor
                )
        }
    }
}