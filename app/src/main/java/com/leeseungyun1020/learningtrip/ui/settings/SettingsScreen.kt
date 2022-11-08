package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.data.AuthRepository
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.account.SignInRequiredScreen
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModel
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModelFactory

@Composable
fun SettingsScreen(navController: NavController, authViewModel: AuthViewModel) {
    val isSignIn by authViewModel.isSignIn.observeAsState(false)
    val user by authViewModel.user.observeAsState()
    if (user == null) {
        authViewModel.loadUserInfo()
    }

    if (isSignIn) {
        LearningTripScaffold(
            title = stringResource(id = R.string.title_my),
        ) {
            Column {
                if (user != null) {
                    user?.apply {
                        NameTab(
                            name = nickname ?: "",
                            email = email ?: "",
                            imageURL = imageURL ?: "",
                            level = level ?: "",
                            onMoveClicked = { navController.navigate(Screen.Account.route) },
                            modifier = Modifier.padding(top = 20.dp, bottom = 13.dp)
                        )
                    }
                } else {
                    Button(
                        onClick = { authViewModel.signOut() }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 48.dp, start = 16.dp, end = 16.dp)
                    ) {
                        Text(text = stringResource(id = R.string.action_signout))
                    }
                }

                Divider(thickness = 6.dp)
                // TODO: 마이페이지 구현
/*
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
*/
                Divider(thickness = 6.dp)

            }
        }
    } else {
        SignInRequiredScreen(
            navController = navController,
            name = stringResource(id = R.string.title_my)
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPrev() {
    val authRepository = AuthRepository(LocalContext.current)
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(authRepository)
    )
    SettingsScreen(navController = rememberNavController(), authViewModel)
}