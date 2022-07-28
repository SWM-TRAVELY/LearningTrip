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
import androidx.navigation.navigation
import com.leeseungyun1020.learningtrip.ui.home.HomeScreen
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme
import com.leeseungyun1020.learningtrip.ui.theme.Primary
import com.leeseungyun1020.learningtrip.ui.theme.Secondary

sealed class NavigationScreen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconId: Int
) {
    object Home : NavigationScreen("home", R.string.nav_home, R.drawable.ic_home)
    object Story : NavigationScreen("story", R.string.nav_story, R.drawable.ic_story)
    object Category : NavigationScreen("category", R.string.nav_category, R.drawable.ic_category)
    object Nearby : NavigationScreen("nearby", R.string.nav_nearby, R.drawable.ic_nearby)
    object My : NavigationScreen("my", R.string.nav_my, R.drawable.ic_my)
}

sealed class Screen(val route: String) {
    object SignIn : Screen("signIn")
    object SignUp : Screen("SingUp")
    object Permission : Screen("permission")
    object Place : Screen("place")
    object AddReview : Screen("addReview")
    object Heritage : Screen("heritage")
    object Search : Screen("search")
    object AddPath : Screen("addPath")
    object Path : Screen("path")
    object Account : Screen("account")
    object MyReview : Screen("myReview")
    object Collection : Screen("collection")
    object Achievement : Screen("achievement")
    object Sticker : Screen("sticker")
    object NoticeList : Screen("noticeList")
    object Notice : Screen("notice")
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
                                NavigationScreen.Category,
                                NavigationScreen.Story,
                                NavigationScreen.Home,
                                NavigationScreen.Nearby,
                                NavigationScreen.My
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
                            startDestination = NavigationScreen.Home.route,
                            Modifier.padding(innerPadding)
                        ) {
                            composable(NavigationScreen.Home.route) {
                                HomeScreen(navController)
                            }
                            composable(NavigationScreen.Category.route) {
                                CategoryScreen(navController)
                            }
                            composable(NavigationScreen.Story.route) {
                                StoryScreen(navController)
                            }
                            composable(NavigationScreen.Nearby.route) {
                                NearbyScreen(navController)
                            }
                            composable(NavigationScreen.My.route) {
                                MyScreen(navController)
                            }

                            navigation(
                                startDestination = Screen.SignIn.route,
                                route = Screen.SignIn.route
                            ) {
                                composable(Screen.SignIn.route) {
                                    SignInScreen(navController)
                                }
                                composable(Screen.SignUp.route) {
                                    SignUpScreen(navController)
                                }
                            }

                            composable(Screen.Permission.route) {
                                PermissionScreen(navController)
                            }

                            composable("${Screen.Place.route}/{id}") {
                                PlaceScreen(navController, it.arguments?.getString("id") ?: "0")
                            }

                            composable("${Screen.AddReview.route}/{placeId}") {
                                AddReviewScreen(
                                    navController,
                                    placeId = it.arguments?.getString("placeId") ?: "0"
                                )
                            }

                            composable("${Screen.Heritage.route}/{id}") {
                                HeritageScreen(navController, it.arguments?.getString("id") ?: "0")
                            }

                            composable("${Screen.Search.route}/{key}") {
                                SearchScreen(navController, it.arguments?.getString("key") ?: "")
                            }

                            composable("${Screen.AddPath.route}/{id}") {
                                AddPathScreen(navController, it.arguments?.getString("id") ?: "0")
                            }

                            composable("${Screen.Path.route}/{id}") {
                                PathScreen(navController, it.arguments?.getString("id") ?: "0")
                            }

                            composable(Screen.Account.route) {
                                AccountScreen(navController)
                            }

                            composable(Screen.MyReview.route) {
                                MyReviewScreen(navController)
                            }

                            composable(Screen.Collection.route) {
                                CollectionScreen(navController)
                            }

                            composable(Screen.Achievement.route) {
                                AchievementScreen(navController)
                            }

                            composable(Screen.Sticker.route) {
                                StickerScreen(navController)
                            }

                            composable(Screen.NoticeList.route) {
                                NoticeListScreen(navController)
                            }

                            composable("${Screen.Notice.route}/{id}") {
                                NoticeScreen(navController, it.arguments?.getString("id") ?: "0")
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
fun PlaceScreen(navController: NavController, id: String) {
    Text(text = "place")
}

@Composable
fun AddReviewScreen(navController: NavController, placeId: String) {
    Text(text = "add review")
}

@Composable
fun HeritageScreen(navController: NavController, id: String) {
    Text(text = "heritage")
}

@Composable
fun SearchScreen(navController: NavController, key: String) {
    Text(text = "search")
}

@Composable
fun AddPathScreen(navController: NavController, id: String) {
    Text(text = "add path")
}

@Composable
fun PathScreen(navController: NavController, id: String) {
    Text(text = "path")
}

@Composable
fun AccountScreen(navController: NavController) {
    Text(text = "account")
}

@Composable
fun MyReviewScreen(navController: NavController) {
    Text(text = "myReview")
}

@Composable
fun CollectionScreen(navController: NavController) {
    Text(text = "collection")
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
    Text(text = "noticeList")
}

@Composable
fun NoticeScreen(navController: NavController, id: String) {
    Text(text = "notice")
}