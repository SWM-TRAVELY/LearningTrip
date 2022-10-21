package com.leeseungyun1020.learningtrip.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.leeseungyun1020.learningtrip.ui.account.SignInScreen
import com.leeseungyun1020.learningtrip.ui.account.SignUpScreen
import com.leeseungyun1020.learningtrip.ui.category.CategoryScreen
import com.leeseungyun1020.learningtrip.ui.course.AddCourseScreen
import com.leeseungyun1020.learningtrip.ui.course.AddPlaceScreen
import com.leeseungyun1020.learningtrip.ui.course.CourseScreen
import com.leeseungyun1020.learningtrip.ui.course.StoryScreen
import com.leeseungyun1020.learningtrip.ui.home.HomeScreen
import com.leeseungyun1020.learningtrip.ui.nearby.NearbyScreen
import com.leeseungyun1020.learningtrip.ui.permission.PermissionScreen
import com.leeseungyun1020.learningtrip.ui.place.HeritageScreen
import com.leeseungyun1020.learningtrip.ui.place.PlaceScreen
import com.leeseungyun1020.learningtrip.ui.search.SearchScreen
import com.leeseungyun1020.learningtrip.ui.settings.*
import com.leeseungyun1020.learningtrip.viewmodel.AuthViewModel
import com.leeseungyun1020.learningtrip.viewmodel.PlaceViewModel

fun NavGraphBuilder.graph(
    navController: NavController,
    placeViewModel: PlaceViewModel,
    authViewModel: AuthViewModel
) {
    composable(NavigationScreen.Home.route) {
        HomeScreen(navController, placeViewModel)
    }
    composable(NavigationScreen.Category.route) {
        CategoryScreen(navController)
    }
    composable(NavigationScreen.Story.route) {
        StoryScreen(navController, authViewModel)
    }
    composable(NavigationScreen.Nearby.route) {
        NearbyScreen(navController)
    }
    composable(NavigationScreen.My.route) {
        MyScreen(navController, authViewModel)
    }

    navigation(
        startDestination = Screen.SignIn.route,
        route = "sign"
    ) {
        composable(Screen.SignIn.route) {
            SignInScreen(navController, authViewModel)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController, authViewModel)
        }
    }

    composable(Screen.Permission.route) {
        PermissionScreen(navController)
    }

    composable(Screen.Place.route) {
        PlaceScreen(navController, placeViewModel, it.arguments?.getString("id") ?: "0")
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
        SearchScreen(navController, placeViewModel, it.arguments?.getString("key") ?: "")
    }

    composable(Screen.AddCourse.route) {
        AddCourseScreen(navController, it.arguments?.getString("id") ?: "0")
    }

    composable(Screen.AddPlace.route) {
        AddPlaceScreen(
            navController,
            it.arguments?.getString("day") ?: "0",
            it.arguments?.getString("sequence") ?: "0",
            placeViewModel
        )
    }

    composable(Screen.Course.route) {
        CourseScreen(navController, it.arguments?.getString("id") ?: "0")
    }

    composable(Screen.Account.route) {
        AccountScreen(navController, authViewModel)
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
}