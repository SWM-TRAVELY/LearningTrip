package com.leeseungyun1020.learningtrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.leeseungyun1020.learningtrip.ui.NavigationScreen
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.graph
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val placeViewModel = PlaceViewModel(this)
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
fun MyScreen(navController: NavController) {

    Column {
        Text(text = "My")
        Button(onClick = { navController.navigate(Screen.Account.route) }) {
            Text(text = "Account")
        }
        Button(onClick = { navController.navigate(Screen.MyReview.route) }) {
            Text(text = "My Review")
        }
        Button(onClick = { navController.navigate(Screen.Collection.route) }) {
            Text(text = "Collection")
        }
        Button(onClick = { navController.navigate(Screen.NoticeList.route) }) {
            Text(text = "Notice List")
        }
    }
}

@Composable
fun SignInScreen(navController: NavController) {
    Text(text = "SignIn")
}

@Composable
fun SignUpScreen(navController: NavController) {
    Text(text = "SignUp")
}

@Composable
fun PermissionScreen(navController: NavController) {
    Text(text = "permission")
}

@Composable
fun AddReviewScreen(navController: NavController, placeId: String) {
    Text(text = "add review")
}

@Composable
fun HeritageScreen(navController: NavController, id: String) {
    Text(text = "heritage $id")
}

@Composable
fun AddCourseScreen(navController: NavController, id: String) {
    Column {
        Text(text = "add course $id")
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Save Course $id")
        }
    }
}

@Composable
fun AccountScreen(navController: NavController) {
    Text(text = "account")
}

@Composable
fun MyReviewScreen(navController: NavController) {
    Column {
        Text(text = "myReview")
        Button(onClick = { navController.navigate("${Screen.Place.root}/101") }) {
            Text(text = "Place 101 Review")
        }
    }

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