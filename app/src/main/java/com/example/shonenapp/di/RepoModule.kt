package com.example.shonenapp.di

import android.content.Context
import com.example.shonenapp.data.repository.DataStoreOperationImpl
import com.example.shonenapp.domain.respository.DataStoreOperation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStoreOperation {
        return DataStoreOperationImpl(context)
    }
}