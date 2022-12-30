package com.example.shonenapp.presentation.screen.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.shonenapp.navigation.Screen
import com.example.shonenapp.presentation.comon.ListCharacter

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allCharacter = homeViewModel.getAllCharacter.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopBar (onSearchClicked = {
                navHostController.navigate(Screen.SearchScreen.route)
            })
        },
    ) {
        ListCharacter(allCharacter, navHostController = navHostController)
        it.calculateBottomPadding()
    }
}