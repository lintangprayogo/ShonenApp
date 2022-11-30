package com.example.shonenapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.shonenapp.navigation.Screen.*
import com.example.shonenapp.utils.Constant.DETAIL_ID

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(startDestination = SplashScreen.route, navController = navHostController) {
        composable(SplashScreen.route) {

        }
        composable(WelcomeScreen.route) {

        }
        composable(HomeScreen.route) {

        }
        composable(Details.route, arguments = listOf(navArgument(DETAIL_ID) {
            type = NavType.IntType
        })) {

        }
        composable(SearchScreen.route) {

        }

    }
}