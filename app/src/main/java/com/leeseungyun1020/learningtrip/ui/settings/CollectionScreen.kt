package com.leeseungyun1020.learningtrip.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.leeseungyun1020.learningtrip.ui.Screen

@Composable
fun CollectionScreen(navController: NavController) {
    Column {
        Text(text = "collection")
        Button(onClick = { navController.navigate(Screen.Achievement.route) }) {
            Text(text = "Achievement")
        }
        Button(onClick = { navController.navigate(Screen.Sticker.route) }) {
            Text(text = "Sticker")
        }
    }

}

@Composable
fun AchievementScreen(navController: NavController) {
    Text(text = "achievement")
}

@Composable
fun StickerScreen(navController: NavController) {
    Text(text = "sticker")
}