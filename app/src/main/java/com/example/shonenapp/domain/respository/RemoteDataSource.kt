package com.example.shonenapp.domain.respository

import androidx.paging.PagingData
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllCharacter(): Flow<PagingData<ShonenCharacterEntry>>
    fun searchCharacter(query: String): Flow<PagingData<ShonenCharacterEntry>>
}