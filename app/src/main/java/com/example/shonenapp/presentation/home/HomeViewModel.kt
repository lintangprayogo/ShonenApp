package com.example.shonenapp.presentation.home

import androidx.lifecycle.ViewModel
import com.example.shonenapp.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (useCases: UseCases):ViewModel() {

    val getAllCharacter = useCases.getAllCharacterUseCase()
}