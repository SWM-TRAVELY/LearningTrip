package com.leeseungyun1020.learningtrip.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningTripScaffold(
    title: String,
    setDisplayHomeAsUpEnabled: Boolean = false,
    setBodyContentInnerPadding: Boolean = true,
    onHomeAsUpClicked: () -> Unit = {},
    topBarExtraContent: @Composable () -> Unit,
    bodyContent: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                    )
            ) {
                Box(modifier = Modifier.height(56.dp)) {
                    if (setDisplayHomeAsUpEnabled)
                        IconButton(
                            onClick = onHomeAsUpClicked,
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(
                                    id = R.string.action_back
                                ),
                                tint = Color.White
                            )
                        }
                    Text(
                        text = title,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }

                topBarExtraContent()
            }
        },
        content = { innerPadding ->
            val modifier =
                if (setBodyContentInnerPadding)
                    Modifier.padding(top = innerPadding.calculateTopPadding())
                else Modifier
            Column(
                modifier =
                modifier.verticalScroll(
                    rememberScrollState()
                )
            ) {
                bodyContent()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LearningTripScaffoldPreview() {
    LearningTripTheme {
        LearningTripScaffold(
            title = "Title",
            setDisplayHomeAsUpEnabled = true,
            setBodyContentInnerPadding = false,
            topBarExtraContent = {
                Text(
                    text = "Extra Content",
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }, bodyContent = {
                Column {
                    Text(
                        text = "Body Content1",
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "Body Content2",
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "Body Content3",
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        text = "Body Content4",
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

            })
    }
}