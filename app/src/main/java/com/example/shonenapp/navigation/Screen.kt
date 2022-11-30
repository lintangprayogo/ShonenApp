package com.example.shonenapp.navigation

import com.example.shonenapp.utils.Constant.DETAIL_ID

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object WelcomeScreen : Screen("welcome_screen")
    object HomeScreen : Screen("home_screen")
    object Details : Screen("details_screen/${DETAIL_ID}") {
        fun passId(id: Int): String {
            return "details_screen/$id"
        }
    }

    object SearchScreen : Screen("search_screen")
}
