package com.example.shonenapp.presentation.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.shonenapp.presentation.comon.ListCharacter

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val searchedCharacter = searchViewModel.searchedCharacter.collectAsLazyPagingItems()

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