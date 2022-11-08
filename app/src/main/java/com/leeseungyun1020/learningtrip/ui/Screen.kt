package com.leeseungyun1020.learningtrip.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.leeseungyun1020.learningtrip.R

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
            R.string.nav_my_course,
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

    object Settings : NavigationScreen(
        "my",
        R.string.nav_settings,
        R.drawable.ic_my,
        listOf(
            "my",
            Screen.Account.route,
            Screen.MyReview.route,
            Screen.Collection.route,
            Screen.Achievement.route,
            Screen.Sticker.route,
            Screen.NoticeList.route,
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
    object AddCourse : Screen("addCourse/{id}?isCopy={isCopy}", "addCourse")
    object AddPlace : Screen("addPlace/{day}/{sequence}", "addPlace")
    object Course : Screen("course/{id}?isEditable={isEditable}", "course")
    object Account : Screen("account", "account")
    object MyReview : Screen("myReview", "myReview")
    object Collection : Screen("collection", "collection")
    object Achievement : Screen("achievement", "achievement")
    object Sticker : Screen("sticker", "sticker")
    object NoticeList : Screen("noticeList", "noticeList")
    object InfoChange : Screen("infoChange", "infoChange")
    object CourseRequest : Screen("courseRequestScreen", "courseRequestScreen")
}