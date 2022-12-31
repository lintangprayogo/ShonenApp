package com.example.shonenapp.presentation.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shonenapp.utils.PalateGenerator
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val selectedCharacter by detailViewModel.selectedCharacter
    val collorPallate by detailViewModel.colorPallate

    selectedCharacter?.let {
        if (collorPallate.isNotEmpty()) {
            DetailContent(
                navHostController = navHostController,
                shonenCharacterEntry = it,
                colors = collorPallate
            )
        } else {
            detailViewModel.generateColorPallate()
        }
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPallater -> {
                    val bitmap = PalateGenerator.convertImageUrlToBitmap(
                        selectedCharacter?.image ?: "",
                        context
                    )

                    if (bitmap != null) {
                        detailViewModel.setCollorPallete(
                            colors = PalateGenerator.extractColorFromBitmap(bitmap)
                        )
                    }
                }
            }
        }

    }

}
