package com.example.shonenapp.domain.use_case.search_character_use_case

import androidx.paging.PagingData
import com.example.shonenapp.data.repository.Repository
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import kotlinx.coroutines.flow.Flow

class SearchCharacterUseCase(private val repository: Repository) {

    operator fun invoke(query: String): Flow<PagingData<ShonenCharacterEntry>> =
        repository.searchCharacter(query)
}