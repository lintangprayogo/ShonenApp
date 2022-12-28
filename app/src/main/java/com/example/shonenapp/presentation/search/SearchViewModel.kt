package com.example.shonenapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import com.example.shonenapp.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery


    private val _searchedCharacter =
        MutableStateFlow<PagingData<ShonenCharacterEntry>>(PagingData.empty())
    val searchedCharacter: StateFlow<PagingData<ShonenCharacterEntry>> = _searchedCharacter

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchCharacter(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchCharacterUseCase.invoke(query).cachedIn(viewModelScope).collect {
                _searchedCharacter.value = it
            }
        }

    }
}