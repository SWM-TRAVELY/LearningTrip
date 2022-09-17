package com.leeseungyun1020.learningtrip.ui.permission

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.R
import com.leeseungyun1020.learningtrip.ui.common.SimpleTitleScaffold
import com.leeseungyun1020.learningtrip.ui.theme.Gray2

@Composable
fun PermissionScreen(navController: NavController) {
    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val permissionRequest = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // TODO: 권한 허용 완료
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // TODO: 권한 허용 완료
            }
            else -> {
                // TODO: 권한 허용 거부
            }
        }
    }
    SimpleTitleScaffold(title = stringResource(id = R.string.title_permission)) {
        Column {
            Text(
                modifier = Modifier.padding(top = 35.dp, start = 16.dp, end = 16.dp),
                text = stringResource(id = R.string.title_location_permission),
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                text = stringResource(id = R.string.desc_location_permission),
                style = MaterialTheme.typography.bodySmall,
                color = Gray2
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 308.dp, start = 16.dp, end = 16.dp),
                onClick = {
                    permissionRequest.launch(permissions)
                },
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(text = stringResource(id = R.string.action_allow_permission))
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PermissionScreenPrev() {
    PermissionScreen(navController = rememberNavController())
}