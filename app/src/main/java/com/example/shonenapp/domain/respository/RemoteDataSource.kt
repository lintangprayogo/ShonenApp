package com.example.shonenapp.domain.respository

import androidx.paging.PagingData
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllData(): Flow<PagingData<ShonenCharacterEntry>>
    fun searchHero(query: String): Flow<PagingData<ShonenCharacterEntry>>
}