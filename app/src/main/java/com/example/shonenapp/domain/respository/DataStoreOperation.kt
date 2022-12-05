package com.example.shonenapp.domain.respository

import kotlinx.coroutines.flow.Flow


interface DataStoreOperation {
    suspend fun saveOnBoardingState(completed:Boolean)
    fun getOnBoardingState(): Flow<Boolean>
}