package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.ui.theme.Gray3

@Composable
fun AccountScreen(navController: NavController) {
    val imageURL = "https://avatars.githubusercontent.com/u/34941061"
    val name = "Name"
    LearningTripScaffold(
        title = stringResource(id = R.string.title_my),
        setDisplayHomeAsUpEnabled = true,
        onHomeAsUpClicked = { navController.popBackStack() }) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 사용자 정보
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageURL)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_baseline_person_24),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .clip(CircleShape)
                    .width(96.dp)
                    .height(96.dp)
                    .align(Alignment.CenterHorizontally),
            )

            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 14.dp, bottom = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Divider(thickness = 6.dp)

            // 나의 정보 수정
            AccountSettingTab(
                title = stringResource(id = R.string.title_edit_info),
                onClick = { /* TODO: 정보 수정 화면으로 이동, navController.navigate() */ },
                modifier = Modifier.height(53.dp)
            )

            // 비밀번호 변경
            AccountSettingTab(
                title = stringResource(id = R.string.title_change_password),
                onClick = { /* TODO: 비밀번호 변경 화면으로 이동, navController.navigate() */ },
                modifier = Modifier.height(53.dp)
            )

            // 로그아웃 / 탈퇴
            Row(
                modifier = Modifier
                    .padding(top = 12.dp, end = 20.dp)
                    .height(IntrinsicSize.Min)
                    .align(Alignment.End)
            ) {
                TextButton(onClick = { /*TODO: 로그아웃*/ }) {
                    Text(
                        text = stringResource(id = R.string.action_signout),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Gray3,
                            fontWeight = FontWeight.Medium
                        ),
                    )
                }
                Divider(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 12.dp, end = 12.dp)
                        .height(10.dp)
                        .width(1.dp)
                )
                TextButton(onClick = { /*TODO: 로그아웃 후 회원탈퇴*/ }) {
                    Text(
                        text = stringResource(id = R.string.action_unregister),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Gray3,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountScreenPrev() {
    AccountScreen(navController = rememberNavController())
}