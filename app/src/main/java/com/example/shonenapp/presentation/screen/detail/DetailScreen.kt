package com.example.shonenapp.presentation.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val selectedCharacter by detailViewModel.selectedCharacter
    selectedCharacter?.let {
      DetailContent(navHostController = navHostController, shonenCharacterEntry = it)
    }
}
