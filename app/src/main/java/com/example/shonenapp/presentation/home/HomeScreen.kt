package com.example.shonenapp.presentation.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(homeViewModel: HomeViewModel= hiltViewModel()) {
    val  allCharacter = homeViewModel.getAllCharacter.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopBar {

            }
        },
    ) {
        it.calculateBottomPadding()
    }
}