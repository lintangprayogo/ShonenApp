package com.example.shonenapp.presentation.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import com.example.shonenapp.domain.use_case.UseCases
import com.example.shonenapp.utils.Constant.DETAIL_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: UseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedCharacter =
        mutableStateOf<ShonenCharacterEntry?>(null)

    val selectedCharacter: State<ShonenCharacterEntry?> = _selectedCharacter


    init {
        getSelectedCharacter()
    }

    fun getSelectedCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            val id = savedStateHandle.get<Long>(DETAIL_ID)
            id?.let {
                val result = useCases.getSelectedCharacterUseCase.invoke(id = id)
                _selectedCharacter.value = result
            }
        }
    }
}