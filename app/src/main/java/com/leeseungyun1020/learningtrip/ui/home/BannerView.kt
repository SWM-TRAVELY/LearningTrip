package com.leeseungyun1020.learningtrip.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.learningtrip.R

@Composable
fun BannerView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BannerViewPreview() {
    BannerView(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(96.dp)
    )
}