package com.example.shonenapp.navigation

import com.example.shonenapp.utils.Constant.DETAIL_ID

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{id}/") {
        fun passId(id:Long): String {
            return "details_screen/$id/"
        }
    }

    object SearchScreen : Screen("search_screen")
}
