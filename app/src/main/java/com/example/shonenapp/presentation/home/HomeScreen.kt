package com.example.shonenapp.presentation.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.shonenapp.presentation.component.RatingWidget

@Composable
fun HomeScreen(homeViewModel: HomeViewModel= hiltViewModel()) {
    val  allCharacter = homeViewModel.getAllCharacter.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopBar {

            }
        },
    ) {
        RatingWidget(modifier = Modifier, rating = 4.5)
        it.calculateBottomPadding()
    }
}