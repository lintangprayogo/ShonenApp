package com.example.shonenapp.domain.get_all_character_use_case

import androidx.paging.PagingData
import com.example.shonenapp.data.repository.Repository
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharacterUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<PagingData<ShonenCharacterEntry>> =
        repository.getAllCharacter()
}