package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.account.SignUpTextField
import com.leeseungyun1020.learningtrip.ui.common.LearningTripScaffold
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModel
import com.leeseungyun1020.learningtrip.viewmodel.InfoChangeViewModel

@Composable
fun InfoChangeScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    viewModel: InfoChangeViewModel = viewModel()
) {
    val user by authViewModel.user.observeAsState()
    if (user == null)
        authViewModel.loadUserInfo()
    else {
        user?.nickname?.let { viewModel.onUpdateNickname(it) }
        user?.phone?.let { viewModel.onUpdatePhone(it) }
    }
    val focusManager = LocalFocusManager.current

    LearningTripScaffold(
        title = stringResource(id = R.string.title_info_change),
        setDisplayHomeAsUpEnabled = true,
        onHomeAsUpClicked = { navController.popBackStack() }) {
        Column(modifier = Modifier.fillMaxSize()) {
            SignUpTextField(
                value = viewModel.nickname,
                onValueChange = { viewModel.onUpdateNickname(it) },
                hintId = R.string.hint_nickname,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 16.dp, end = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            )
            SignUpTextField(
                value = viewModel.phone,
                onValueChange = { viewModel.onUpdatePhone(it) },
                hintId = R.string.hint_phone,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    if (viewModel.check()) {
                        authViewModel.updateUserInfo(viewModel.nickname, viewModel.phone)
                        navController.popBackStack()
                    }
                }),
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp),
                onClick = {
                    if (viewModel.check()) {
                        authViewModel.updateUserInfo(viewModel.nickname, viewModel.phone)
                        navController.popBackStack()
                    }
                },
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(text = stringResource(id = R.string.action_update))
            }
        }
    }

}