package com.leeseungyun1020.learningtrip.ui.account

import android.content.Intent
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.data.AuthRepository
import com.leeseungyun1020.learningtrip.data.TAG
import com.leeseungyun1020.learningtrip.network.AUTH_KAKAO_URL
import com.leeseungyun1020.learningtrip.network.AUTH_NAVER_URL
import com.leeseungyun1020.learningtrip.network.BASE_URL
import com.leeseungyun1020.learningtrip.ui.NavigationScreen
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.common.SimpleTitleScaffold
import com.leeseungyun1020.learningtrip.ui.theme.KakaoYellow
import com.leeseungyun1020.learningtrip.ui.theme.naverGreen
import com.leeseungyun1020.learningtrip.ui.theme.notoSansKRFamily
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModel
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModelFactory
import org.json.JSONObject

enum class SignInType {
    INIT, EMAIL, KAKAO, NAVER
}

@Composable
fun SignInScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val type = rememberSaveable { mutableStateOf(SignInType.INIT) }
    when (type.value) {
        SignInType.INIT -> SignInInitScreen(navController, authViewModel) {
            type.value = it
        }
        SignInType.EMAIL -> SignInEmailScreen(navController, authViewModel) {
            type.value = it
        }
        SignInType.KAKAO -> SignInOAuthScreen(navController, authViewModel, SignInType.KAKAO) {
            type.value = it
        }
        SignInType.NAVER -> SignInOAuthScreen(navController, authViewModel, SignInType.NAVER) {
            type.value = it
        }
    }
}

@Composable
fun SignInInitScreen(
    navController: NavController,
    viewModel: AuthViewModel,
    onClick: (SignInType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            modifier = Modifier.padding(top = 16.dp),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.hello), style = MaterialTheme.typography.titleLarge
        )
        SignInButton(
            onClick = { onClick(SignInType.KAKAO) },
            backgroundColor = KakaoYellow,
            textColor = Color(0xD9000000),
            painterId = R.drawable.kakao_icon,
            stringId = R.string.action_kakao_login,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 142.dp)
        )

        SignInButton(
            onClick = { onClick(SignInType.NAVER) },
            backgroundColor = naverGreen,
            textColor = Color.White,
            painterId = R.drawable.naver_icon,
            stringId = R.string.action_naver_login,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 8.dp)
        )

        Row(
            modifier = Modifier
                .padding(top = 28.dp)
                .height(IntrinsicSize.Min)
        ) {
            Text(
                text = stringResource(id = R.string.action_email_login),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.clickable {
                    onClick(SignInType.EMAIL)
                },
            )

            Divider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxHeight()
                    .width(1.dp)
            )

            Text(text = stringResource(id = R.string.action_email_signup),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.SignUp.route)
                })
        }
    }
}

@Composable
fun SignInEmailScreen(
    navController: NavController,
    viewModel: AuthViewModel,
    onClick: (SignInType) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val isSignIn by viewModel.isSignIn.observeAsState(false)
    val signInError by viewModel.signInError.observeAsState(false)
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    BackHandler {
        onClick(SignInType.INIT)
    }

    if (isSignIn) {
        navController.popBackStack(NavigationScreen.Home.route, false)
    }

    if (signInError) {
        AlertDialog(
            onDismissRequest = {
                viewModel.signInError.value = false
            },
            title = {
                Text(
                    text = stringResource(id = R.string.title_signin),
                    fontFamily = notoSansKRFamily
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.error_signin),
                    style = MaterialTheme.typography.bodyMedium
                )

            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.signInError.value = false
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

    SimpleTitleScaffold(title = stringResource(id = R.string.title_signin)) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            SignUpTextField(
                value = email,
                onValueChange = { email = it },
                hintId = R.string.hint_email,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            )

            SignUpTextField(
                value = password,
                onValueChange = { password = it },
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
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.signIn(email, password)
                }),
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp),
                onClick = {
                    viewModel.signIn(email, password)
                },
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(text = stringResource(id = R.string.title_signin))
            }
        }
    }
}


@Composable
fun SignInOAuthScreen(
    navController: NavController,
    viewModel: AuthViewModel,
    type: SignInType,
    onClick: (SignInType) -> Unit,
) {
    val authURL = when (type) {
        SignInType.KAKAO -> AUTH_KAKAO_URL
        SignInType.NAVER -> AUTH_NAVER_URL
        else -> ""
    }
    BackHandler {
        onClick(SignInType.INIT)
    }
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.run {
                javaScriptEnabled = true
                javaScriptCanOpenWindowsAutomatically = true
                setSupportMultipleWindows(true)
            }
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    Log.d("LSYD", "onPageFinished: $url")
                    if (url?.startsWith("${BASE_URL}login") == true) {
                        Log.d("LSYD", "url: $url")
                        view?.evaluateJavascript(
                            """
                        function getHTML() {
                            return document.body.firstChild.textContent;
                        }
                        getHTML();
                    """.trimIndent()
                        ) { html ->
                            Log.d("LSYD", "HTML: $html")
                            if (html != null) {
                                try {
                                    val jsonObject = JSONObject(
                                        html.removePrefix("\"").removeSuffix("\"").replace("\\", "")
                                    )
                                    if (jsonObject.getInt("status") == 200) {
                                        val data = jsonObject.getJSONObject("data")
                                        val token = data.getString("access_token")
                                        val refreshToken = data.getString("refresh_token")
                                        Log.d("LSYD", "token: $token refreshToken: $refreshToken")
                                        viewModel.signInWithTokens(refreshToken, token)
                                        navController.popBackStack()
                                    }
                                } catch (e: Exception) {
                                    Log.d("LSYD", "Exception: $e")
                                }
                            }
                        }
                    }

                    super.onPageFinished(view, url)

                }

                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
                    Log.d("LSYD", "shouldOverrideUrlLoading: ${request.url}")
                    val intent = Intent.parseUri(request.url.toString(), Intent.URI_INTENT_SCHEME)

                    if (intent.resolveActivity(context.packageManager) != null) {
                        startActivity(context, intent, null)
                        Log.d(TAG, "ACTIVITY: ${intent.`package`}")
                        return true
                    }
                    return super.shouldOverrideUrlLoading(view, request)
                }
            }
            loadUrl(authURL)
        }
    }, update = {
        it.loadUrl(authURL)
    })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInScreenPrev() {
    val authRepository = AuthRepository(LocalContext.current)
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(authRepository)
    )
    SignInScreen(navController = rememberNavController(), authViewModel)
}