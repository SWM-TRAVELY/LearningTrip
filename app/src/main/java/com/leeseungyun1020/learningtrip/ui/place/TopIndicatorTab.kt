package com.leeseungyun1020.learningtrip.ui.place

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leeseungyun1020.learningtrip.ui.theme.Gray2
import com.leeseungyun1020.learningtrip.ui.theme.Gray4

@Composable
fun TopIndicatorTab(
    titles: List<String>,
    pages: List<@Composable () -> Unit>,
) {
    var state by remember { mutableStateOf(0) }

    TabRow(selectedTabIndex = state,
        indicator = {}
    ) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = state == index,
                onClick = { state = index }
            ) {
                Column(
                    Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .background(
                            if (state == index)
                                MaterialTheme.colorScheme.background
                            else
                                Gray4
                        ),
                ) {
                    // Top indicator
                    if (state == index)
                        Box(
                            Modifier
                                .height(5.dp)
                                .fillMaxWidth()
                                .background(
                                    MaterialTheme.colorScheme.primary
                                )
                        )
                    else
                        Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 12.dp),
                        fontSize = 14.sp,
                        color = if (state == index) MaterialTheme.colorScheme.primary
                        else Gray2
                    )
                }
            }
        }
    }
    pages[state]()
}