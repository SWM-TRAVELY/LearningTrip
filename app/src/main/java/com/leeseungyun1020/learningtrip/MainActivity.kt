package com.leeseungyun1020.learningtrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.ui.home.HomeScreen
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme
import com.leeseungyun1020.learningtrip.ui.theme.Primary
import com.leeseungyun1020.learningtrip.ui.theme.Secondary

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconId: Int
) {
    object Home : Screen("home", R.string.nav_home, R.drawable.ic_home)
    object Story : Screen("story", R.string.nav_story, R.drawable.ic_story)
    object Category : Screen("category", R.string.nav_category, R.drawable.ic_category)
    object Nearby : Screen("nearby", R.string.nav_nearby, R.drawable.ic_nearby)
    object My : Screen("my", R.string.nav_my, R.drawable.ic_my)
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            LearningTripTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            val items = listOf(
                                Screen.Category,
                                Screen.Story,
                                Screen.Home,
                                Screen.Nearby,
                                Screen.My
                            )
                            NavigationBar {
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
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                // Pop up to the start destination of the graph to
                                                // avoid building up a large stack of destinations
                                                // on the back stack as users select items
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                // Avoid multiple copies of the same destination when
                                                // reselecting the same item
                                                launchSingleTop = true
                                                // Restore state when reselecting a previously selected item
                                                restoreState = true
                                            }
                                        },
                                        alwaysShowLabel = true,
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = Primary,
                                            selectedTextColor = Primary,
                                            indicatorColor = Secondary

                                        )
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        NavHost(
                            navController,
                            startDestination = Screen.Home.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(Screen.Home.route) {
                                HomeScreen(navController)
                            }
                            composable(Screen.Category.route) {
                                CategoryScreen(navController)
                            }
                            composable(Screen.Story.route) {
                                StoryScreen(navController)
                            }
                            composable(Screen.Nearby.route) {
                                NearbyScreen(navController)
                            }
                            composable(Screen.My.route) {
                                MyScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearningTripTheme {
        HomeScreen(navController = rememberNavController())
    }
}

@Composable
fun CategoryScreen(navController: NavController) {
    Text(text = "Category")
}

@Composable
fun StoryScreen(navController: NavController) {
    Text(text = "Story")
}

@Composable
fun NearbyScreen(navController: NavController) {
    Text(text = "Nearby")
}

@Composable
fun MyScreen(navController: NavController) {
    Text(text = "My")
}