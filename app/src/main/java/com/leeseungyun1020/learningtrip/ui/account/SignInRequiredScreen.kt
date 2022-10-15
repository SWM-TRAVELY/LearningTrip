package com.leeseungyun1020.learningtrip.ui.account

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.Screen

@Composable
fun SignInRequiredScreen(
    navController: NavController,
    name: String,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name, style = MaterialTheme.typography.bodyLarge)
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = R.string.desc_signin_required),
            style = MaterialTheme.typography.bodyMedium
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp),
            onClick = {
                navController.navigate(Screen.SignIn.route)
            },
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = stringResource(id = R.string.title_signin))
        }
    }
}

@Preview
@Composable
fun SignInRequiredScreenPrev() {
    SignInRequiredScreen(navController = rememberNavController(), name = "테스트")
}