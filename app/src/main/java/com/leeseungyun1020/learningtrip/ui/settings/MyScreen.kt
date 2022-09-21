package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold

@Composable
fun MyScreen(navController: NavController) {
    LearningTripScaffold(
        title = stringResource(id = R.string.title_my),
    ) {
        Column {
            NameTab(
                name = "이승윤",
                email = "ileilliat@gmail.com",
                imageURL = "https://avatars.githubusercontent.com/u/34941061",
                onMoveClicked = { navController.navigate(Screen.Account.route) })

            Divider(thickness = 6.dp)

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                SettingIconButton(
                    painter = painterResource(id = R.drawable.ic_collection),
                    title = stringResource(id = R.string.title_collection),
                    onClick = { navController.navigate(Screen.Collection.route) }
                )
                SettingIconButton(
                    painter = painterResource(id = R.drawable.ic_review),
                    title = stringResource(id = R.string.title_review),
                    onClick = { navController.navigate(Screen.MyReview.route) }
                )
                SettingIconButton(
                    painter = painterResource(id = R.drawable.ic_notice),
                    title = stringResource(id = R.string.title_notice),
                    onClick = { navController.navigate(Screen.NoticeList.route) }
                )
            }

            Divider(thickness = 6.dp)

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyScreenPrev() {
    MyScreen(navController = rememberNavController())
}