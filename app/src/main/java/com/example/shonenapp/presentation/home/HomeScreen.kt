package com.example.shonenapp.presentation.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.shonenapp.presentation.component.ListCharacter

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allCharacter = homeViewModel.getAllCharacter.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopBar {

            }
        },
    ) {
        ListCharacter(allCharacter, navHostController = navHostController)
        it.calculateBottomPadding()
    }
}