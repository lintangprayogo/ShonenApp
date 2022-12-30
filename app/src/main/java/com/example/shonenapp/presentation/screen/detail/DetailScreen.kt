package com.example.shonenapp.presentation.screen.detail

import androidx.compose.material.Text
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
        Text(text = it.name)
    }
}
