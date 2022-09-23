package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.learningtrip.R

@Composable
fun AccountSettingTab(title: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick() }) {
        Text(
            text = title, modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
        )
        Icon(
            imageVector = Icons.Default.NavigateNext,
            contentDescription = stringResource(id = R.string.action_move),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)
        )
        Divider(thickness = 1.dp, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Preview(showBackground = true)
@Composable
fun AccountSettingTabPrev() {
    AccountSettingTab(
        "비밀번호 변경",
        {},
        modifier = Modifier.height(48.dp)
    )
}