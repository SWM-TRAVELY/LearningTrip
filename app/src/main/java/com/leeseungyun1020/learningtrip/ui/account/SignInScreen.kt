package com.leeseungyun1020.learningtrip.ui.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.theme.KakaoYellow
import com.leeseungyun1020.learningtrip.ui.theme.naverGreen

@Composable
fun SignInScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(top = 140.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = stringResource(id = R.string.hello), style = MaterialTheme.typography.titleLarge
        )
        SignInButton(
            onClick = { /*TODO*/ },
            backgroundColor = KakaoYellow,
            textColor = Color(0xD9000000),
            painterId = R.drawable.kakao_icon,
            stringId = R.string.action_kakao_login,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 142.dp)
        )

        SignInButton(
            onClick = { /*TODO*/ },
            backgroundColor = naverGreen,
            textColor = Color.White,
            painterId = R.drawable.naver_icon,
            stringId = R.string.action_naver_login,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 8.dp)
        )

        Row(modifier = Modifier.padding(top = 28.dp).height(IntrinsicSize.Min)) {
            Text(
                text = stringResource(id = R.string.action_email_login),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Divider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxHeight()
                    .width(1.dp)
            )

            Text(
                text = stringResource(id = R.string.action_email_signup),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInScreenPrev() {
    SignInScreen(navController = rememberNavController())
}