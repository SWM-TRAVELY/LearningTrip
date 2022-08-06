package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme

@Composable
fun CelledTextListView(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleColor: Color = Color.Black,
    items: List<Pair<String, String>>,
    fontColor: Color = Color.Black,
    showDialog: Boolean = false
) {
    var openDialog by remember { mutableStateOf(false) }
    if (showDialog && openDialog) {
        AlertDialog(
            onDismissRequest = {
                openDialog = false
            },
            title = {
                Text(text = title ?: stringResource(id = R.string.detail))
            },
            text = {
                Column {
                    for (item in items) {
                        Text(
                            text = item.first, fontSize = 14.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                        Text(text = item.second, fontSize = 12.sp)
                    }
                }

            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text(stringResource(id = R.string.action_confirm))
                }
            },
        )
    }
    Column(modifier = modifier
        .fillMaxWidth()
        .clickable { openDialog = true }) {
        if (!title.isNullOrEmpty())
            Text(text = title, color = titleColor, fontSize = 14.sp)
        Row(modifier = if (title.isNullOrEmpty()) Modifier else Modifier.padding(top = 2.dp)) {
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
                        text = item.second, fontSize = 12.sp, lineHeight = 12.sp, color = fontColor,
                        maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CelledTextListViewPreview() {
    LearningTripTheme {
        CelledTextListView(items = listOf("1" to "첫번째", "2" to "두번째", "3" to "세번째"))
    }
}