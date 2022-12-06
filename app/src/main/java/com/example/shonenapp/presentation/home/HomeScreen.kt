package com.example.shonenapp.presentation.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeTopBar {

            }
        },
    ) {
        it.calculateBottomPadding()
    }
}