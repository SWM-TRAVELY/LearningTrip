package com.leeseungyun1020.learningtrip.ui.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.data.AuthRepository
import com.leeseungyun1020.learningtrip.ui.NavigationScreen
import com.leeseungyun1020.learningtrip.ui.common.SimpleTitleScaffold
import com.leeseungyun1020.learningtrip.ui.theme.notoSansKRFamily
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModel
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModelFactory
import com.leeseungyun1020.learningtrip.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    viewModel: SignUpViewModel = viewModel()
) {
    SimpleTitleScaffold(title = stringResource(id = R.string.title_signup)) {
        val isSignIn by authViewModel.isSignIn.observeAsState(false)
        val signUpError by authViewModel.signUpError.observeAsState(false)
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        val focusManager = LocalFocusManager.current


        if (isSignIn) {
            navController.popBackStack(NavigationScreen.Home.route, false)
        }

        if (signUpError) {
            AlertDialog(
                onDismissRequest = {
                    authViewModel.refreshSignUpError()
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.title_signup),
                        fontFamily = notoSansKRFamily
                    )
                },
                text = {
                    Text(
                        text = stringResource(id = R.string.error_signup),
                        style = MaterialTheme.typography.bodyMedium
                    )

                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            authViewModel.refreshSignUpError()
                        }
                    ) {
                        Text(
                            stringResource(id = R.string.action_confirm),
                            fontFamily = notoSansKRFamily
                        )
                    }
                },
            )
        }

        Column(
            Modifier.verticalScroll(rememberScrollState())
        ) {
            SignUpTextField(
                value = viewModel.name,
                onValueChange = { viewModel.onUpdateName(it) },
                hintId = R.string.hint_name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            )

            if (viewModel.isNameError) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.error_name),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.desc_name),
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            SignUpTextField(
                value = viewModel.email,
                onValueChange = { viewModel.onUpdateEmail(it) },
                hintId = R.string.hint_email,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            )

            if (viewModel.isEmailError) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.error_email),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.desc_email),
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            SignUpTextField(
                value = viewModel.password,
                onValueChange = { viewModel.onUpdatePassword(it) },
                hintId = R.string.hint_password,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = stringResource(id = if (passwordVisible) R.string.action_hide_password else R.string.action_show_password),
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            )

            if (viewModel.isPasswordError) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.error_password),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.desc_password),
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            SignUpTextField(
                value = viewModel.passwordCheck,
                onValueChange = { viewModel.onUpdatePasswordCheck(it) },
                hintId = R.string.hint_password_check,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            )

            if (!viewModel.isPasswordChecked) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.error_password_check),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.desc_password_check),
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            SignUpTextField(
                value = viewModel.nickname,
                onValueChange = { viewModel.onUpdateNickname(it) },
                hintId = R.string.hint_nickname,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            )

            if (viewModel.isNicknameError) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.error_nickname),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.desc_nickname),
                    style = MaterialTheme.typography.bodySmall,
                )
            }

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
                    viewModel.onSignUp()
                    if (!viewModel.isNameError && !viewModel.isEmailError &&
                        viewModel.isPasswordChecked && !viewModel.isPasswordError &&
                        !viewModel.isNicknameError && !viewModel.isPhoneError
                    ) {
                        authViewModel.signUp(
                            viewModel.email,
                            viewModel.password,
                            viewModel.nickname,
                            viewModel.phone
                        )
                    }
                }),
            )

            if (viewModel.isPhoneError) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.error_phone),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.desc_phone),
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp),
                onClick = {
                    viewModel.onSignUp()
                    if (!viewModel.isNameError && !viewModel.isEmailError &&
                        viewModel.isPasswordChecked && !viewModel.isPasswordError &&
                        !viewModel.isNicknameError && !viewModel.isPhoneError
                    ) {
                        authViewModel.signUp(
                            viewModel.email,
                            viewModel.password,
                            viewModel.nickname,
                            viewModel.phone
                        )
                    }
                },
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(text = stringResource(id = R.string.title_signup))
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreenPrev() {
    val authRepository = AuthRepository(LocalContext.current)
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(authRepository)
    )
    SignUpScreen(navController = rememberNavController(), authViewModel)
}
