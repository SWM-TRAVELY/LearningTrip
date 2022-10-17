package com.leeseungyun1020.learningtrip.ui.category

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.leeseungyun1020.learningtrip.ui.Screen

@Composable
fun CategoryScreen(navController: NavController) {
    Column {
        Text(text = "Category")
        Button(onClick = { navController.navigate("${Screen.Search.root}/keyword") }) {
            Text(text = "Search keyword")
        }
    }
}