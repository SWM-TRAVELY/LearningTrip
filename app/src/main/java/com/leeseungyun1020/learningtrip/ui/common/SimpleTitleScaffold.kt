package com.leeseungyun1020.learningtrip.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTitleScaffold(
    title: String,
    bodyContent: @Composable () -> Unit,
) {
    Scaffold(topBar = {
        Column {
            Text(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth()
                    .padding(top = 11.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                text = title
            )
            Divider()
        }
    }, content = { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            bodyContent()
        }
    })
}