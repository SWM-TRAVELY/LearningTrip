package com.leeseungyun1020.learningtrip.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextListView(
    modifier: Modifier = Modifier,
    textList: List<String>,
    onTextClicked: (String) -> Unit
) {
    Column(modifier = modifier) {
        for (text in textList) {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onTextClicked(text)
                    }
                    .padding(12.dp),
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextListViewPreview() {
    TextListView(textList = listOf("첫번째", "두번째", "세번째"), onTextClicked = {})
}