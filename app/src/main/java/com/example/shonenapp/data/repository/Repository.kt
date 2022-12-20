package com.example.shonenapp.data.repository

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

}