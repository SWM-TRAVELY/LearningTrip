package com.leeseungyun1020.learningtrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leeseungyun1020.learningtrip.ui.home.HomeScreen
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme
import com.leeseungyun1020.learningtrip.ui.theme.Primary
import com.leeseungyun1020.learningtrip.ui.theme.Secondary

sealed class NavigationScreen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconId: Int,
    val screenRoutes: List<String>,
) {
    object Home : NavigationScreen(
        "home",
        R.string.nav_home,
        R.drawable.ic_home,
        listOf("home")
    )

    object Story :
        NavigationScreen(
            "story",
            R.string.nav_story,
            R.drawable.ic_story,
            listOf(
                "story",
                Screen.AddCourse.route,
                Screen.Course.route
            )
        )

    object Category : NavigationScreen(
        "category",
        R.string.nav_category,
        R.drawable.ic_category,
        listOf(
            "category",
            Screen.Place.route,
            Screen.AddReview.route,
            Screen.Heritage.route,
            Screen.Search.route
        )
    )

    object Nearby :
        NavigationScreen(
            "nearby",
            R.string.nav_nearby,
            R.drawable.ic_nearby,
            listOf("nearby")
        )

    object My : NavigationScreen(
        "my",
        R.string.nav_my,
        R.drawable.ic_my,
        listOf(
            "my",
            Screen.Account.route,
            Screen.MyReview.route,
            Screen.Collection.route,
            Screen.Achievement.route,
            Screen.Sticker.route,
            Screen.NoticeList.route,
            Screen.Notice.route
        )
    )
}

sealed class Screen(val route: String, val root: String) {
    object SignIn : Screen("signIn", "signIn")
    object SignUp : Screen("SingUp", "signUp")
    object Permission : Screen("permission", "permission")
    object Place : Screen("place/{id}", "place")
    object AddReview : Screen("addReview/{placeId}", "addReview")
    object Heritage : Screen("heritage/{id}", "heritage")
    object Search : Screen("search/{key}", "search")
    object AddCourse : Screen("addCourse/{id}", "addCourse")
    object Course : Screen("course/{id}", "course")
    object Account : Screen("account", "account")
    object MyReview : Screen("myReview", "myReview")
    object Collection : Screen("collection", "collection")
    object Achievement : Screen("achievement", "achievement")
    object Sticker : Screen("sticker", "sticker")
    object NoticeList : Screen("noticeList", "noticeList")
    object Notice : Screen("notice/{id}", "notice")
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
                            graph(navController)
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
    Column {
        Text(text = "Category")
        Button(onClick = { navController.navigate("${Screen.Search.root}/keyword") }) {
            Text(text = "Search keyword")
        }
    }
}

@Composable
fun StoryScreen(navController: NavController) {
    Column {
        Text(text = "Story")
        Button(onClick = { navController.navigate("${Screen.AddCourse.root}/1") }) {
            Text(text = "Add Course 1")
        }
        Button(onClick = { navController.navigate("${Screen.Course.root}/11") }) {
            Text(text = "Course 11")
        }
    }
}

@Composable
fun NearbyScreen(navController: NavController) {
    Text(text = "Nearby")
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
fun PlaceScreen(navController: NavController, id: String) {
    Column {
        Text(text = "place $id")
        Button(onClick = { navController.navigate("${Screen.Heritage.root}/100") }) {
            Text(text = "Heritage 100")
        }
    }

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
fun SearchScreen(navController: NavController, key: String) {
    Column {
        Text(text = "search '$key'")
        Button(onClick = { navController.navigate("${Screen.Place.root}/100") }) {
            Text(text = "Place 100")
        }
        Button(onClick = { navController.navigate("${Screen.Course.root}/200") }) {
            Text(text = "Course 200")
        }
    }
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
fun CourseScreen(navController: NavController, id: String) {
    Column {
        Text(text = "course $id")
        Button(onClick = { navController.navigate("${Screen.AddCourse.root}/${id}") }) {
            Text(text = "Add Course $id")
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