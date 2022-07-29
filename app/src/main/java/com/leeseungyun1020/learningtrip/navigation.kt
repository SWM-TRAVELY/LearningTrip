package com.leeseungyun1020.learningtrip

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.leeseungyun1020.learningtrip.ui.home.HomeScreen

fun NavGraphBuilder.graph(navController: NavController) {
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
        route = "sign"
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

    composable(Screen.Place.route) {
        PlaceScreen(navController, it.arguments?.getString("id") ?: "0")
    }

    composable(Screen.AddReview.route) {
        AddReviewScreen(
            navController,
            placeId = it.arguments?.getString("placeId") ?: "0"
        )
    }

    composable(Screen.Heritage.route) {
        HeritageScreen(navController, it.arguments?.getString("id") ?: "0")
    }

    composable(Screen.Search.route) {
        SearchScreen(navController, it.arguments?.getString("key") ?: "")
    }

    composable(Screen.AddPath.route) {
        AddPathScreen(navController, it.arguments?.getString("id") ?: "0")
    }

    composable(Screen.Path.route) {
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

    composable(Screen.Notice.route) {
        NoticeScreen(navController, it.arguments?.getString("id") ?: "0")
    }
}