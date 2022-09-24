package com.leeseungyun1020.learningtrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.data.AppDatabase
import com.leeseungyun1020.learningtrip.data.PlaceRepository
import com.leeseungyun1020.learningtrip.ui.NavigationScreen
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.graph
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModel
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModelFactory

class MainActivity : ComponentActivity() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { PlaceRepository(database.placeDao()) }
    val placeViewModel: PlaceViewModel by viewModels {
        PlaceViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        placeViewModel.updatePlaceData(this)
        setContent {
            MainScreen(placeViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(placeViewModel: PlaceViewModel) {
    val navController = rememberNavController()
    val isPlaceUpdated by placeViewModel.isUpdated.observeAsState()
    LearningTripTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (isPlaceUpdated == true) {
                Scaffold(
                    bottomBar = {
                        val items = listOf(
                            NavigationScreen.Category,
                            NavigationScreen.Story,
                            NavigationScreen.Home,
                            NavigationScreen.Nearby,
                            NavigationScreen.My
                        )
                        NavigationBar(
                            containerColor = Color.Transparent
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            items.forEach { screen ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            imageVector = ImageVector.vectorResource(id = screen.iconId),
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(stringResource(screen.resourceId)) },
                                    selected = currentDestination?.hierarchy?.any { it.route in screen.screenRoutes } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = false
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    alwaysShowLabel = true,
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.background,

                                        )
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = NavigationScreen.Home.route,
                        Modifier.padding(innerPadding)
                    ) {
                        graph(navController, placeViewModel)
                    }
                }
            } else {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LearningTripTheme {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun CategoryScreen(navController: NavController) {
    Column {
        Text(text = "Category")
        Button(onClick = { navController.navigate("${Screen.Search.root}/keyword") }) {
            Text(text = "Search keyword")
        }
    }
}

@Composable
fun NearbyScreen(navController: NavController) {
    Text(text = "Nearby")
    CircularProgressIndicator()
}

@Composable
fun AddReviewScreen(navController: NavController, placeId: String) {
    Text(text = "add review $placeId")
}

@Composable
fun HeritageScreen(navController: NavController, id: String) {
    Text(text = "heritage $id")
}

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

@Composable
fun NoticeListScreen(navController: NavController) {
    Column {
        Text(text = "noticeList")
        Button(onClick = { navController.navigate("${Screen.Notice.root}/1") }) {
            Text(text = "Notice 1")
        }
    }

}

@Composable
fun NoticeScreen(navController: NavController, id: String) {
    Text(text = "notice $id")
}