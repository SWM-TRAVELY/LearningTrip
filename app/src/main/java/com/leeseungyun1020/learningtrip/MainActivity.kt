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
import com.leeseungyun1020.learningtrip.data.AuthRepository
import com.leeseungyun1020.learningtrip.data.PlaceRepository
import com.leeseungyun1020.learningtrip.ui.NavigationScreen
import com.leeseungyun1020.learningtrip.ui.Screen
import com.leeseungyun1020.learningtrip.ui.graph
import com.leeseungyun1020.learningtrip.ui.theme.LearningTripTheme
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModel
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModelFactory
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModel
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModelFactory

class MainActivity : ComponentActivity() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val placeRepository by lazy { PlaceRepository(database.placeDao()) }
    val placeViewModel: PlaceViewModel by viewModels {
        PlaceViewModelFactory(placeRepository)
    }
    val authRepository by lazy { AuthRepository(this) }
    val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(authRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        placeViewModel.updatePlaceData(this)
        authViewModel.autoSignIn()
        setContent {
            MainScreen(placeViewModel, authViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(placeViewModel: PlaceViewModel, authViewModel: AuthViewModel) {
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
                            // NavigationScreen.Nearby,
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
                        graph(navController, placeViewModel, authViewModel)
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