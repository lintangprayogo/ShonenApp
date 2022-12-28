package com.example.shonenapp.presentation.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel= hiltViewModel()) {
    val searchQuery by searchViewModel.searchQuery
    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChanged = {
                    searchViewModel.setSearchQuery(it)
                }, onSearchClicked = {

                },
                onCloseClicked = {
                    navHostController.popBackStack()
                }
            )
        },
    ) {
        it.calculateBottomPadding()
    }
}