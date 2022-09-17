package com.leeseungyun1020.learningtrip.ui.account

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leeseungyun1020.learningtrip.R

@Composable
fun SignInButton(onClick: () -> Unit, backgroundColor: Color, textColor: Color, @DrawableRes painterId: Int, @StringRes stringId: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(48.dp)
            .background(color = backgroundColor)
            .clip(RoundedCornerShape(5.dp))
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = painterId),
            contentDescription = stringResource(id = R.string.action_kakao_login)
        )

        Text(
            text = stringResource(id = stringId),
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Preview
@Composable
fun SingInButtonPrev() {
    SignInButton(
        onClick = {  },
        backgroundColor = Color.Blue,
        textColor = Color.White,
        painterId = R.drawable.naver_icon,
        stringId = R.string.action_naver_login,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    )
}