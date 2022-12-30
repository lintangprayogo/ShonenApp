package com.example.shonenapp.presentation.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shonenapp.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val useCases: UseCases):ViewModel() {

    fun saveOnboardingStatus(completed:Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveOnboardingUseCase.invoke(completed)
        }
    }
}