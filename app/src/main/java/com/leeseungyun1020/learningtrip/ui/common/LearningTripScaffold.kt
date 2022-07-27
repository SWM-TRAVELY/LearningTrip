package com.leeseungyun1020.learningtrip.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningTripScaffold(
    topBarExtraContent: @Composable () -> Unit,
    bodyContent: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(
                    color = Primary,
                    shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White
                )
                topBarExtraContent()
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(top = innerPadding.calculateTopPadding())
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                bodyContent()
            }
        }
    )
}