package com.example.shonenapp.data.repository

import androidx.paging.PagingData
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import com.example.shonenapp.domain.respository.DataStoreOperation
import com.example.shonenapp.domain.respository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperation,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed)
    }

    fun getOnBoardingState(): Flow<Boolean> = dataStore.getOnBoardingState()


    fun getAllCharacter(): Flow<PagingData<ShonenCharacterEntry>> = remoteDataSource.getAllCharacter()

    fun searchCharacter(query: String): Flow<PagingData<ShonenCharacterEntry>> = remoteDataSource.searchCharacter(query)
}