package com.example.shonenapp.presentation.screen.search

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.shonenapp.presentation.comon.ListCharacter
import com.example.shonenapp.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val searchedCharacter = searchViewModel.searchedCharacter.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChanged = {
                    searchViewModel.setSearchQuery(it)
                }, onSearchClicked = {
                    searchViewModel.searchCharacter(it)
                },
                onCloseClicked = {
                    navHostController.popBackStack()
                }
            )
        },
    ) {
        ListCharacter(entries = searchedCharacter, navHostController = navHostController)
        it.calculateBottomPadding()
    }
}