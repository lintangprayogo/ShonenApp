package com.example.shonenapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shonenapp.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val useCases: UseCases):ViewModel(){

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    fun setSearchQuery(query:String){
        _searchQuery.value = query
    }
}