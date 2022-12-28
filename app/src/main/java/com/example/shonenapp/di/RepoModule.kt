package com.example.shonenapp.di

import android.content.Context
import com.example.shonenapp.data.repository.DataStoreOperationImpl
import com.example.shonenapp.data.repository.Repository
import com.example.shonenapp.domain.get_all_character_use_case.GetAllCharacterUseCase
import com.example.shonenapp.domain.respository.DataStoreOperation
import com.example.shonenapp.domain.use_case.UseCases
import com.example.shonenapp.domain.use_case.get_onboarding_use_case.GetOnboardingUseCase
import com.example.shonenapp.domain.use_case.save_onboarding_use_case.SaveOnboardingUseCase
import com.example.shonenapp.domain.use_case.search_character_use_case.SearchCharacterUseCase
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

    @Singleton
    @Provides
    fun provideUseCase(repository: Repository): UseCases {
        return UseCases(
            getOnboardingUseCase = GetOnboardingUseCase(repository = repository),
            saveOnboardingUseCase = SaveOnboardingUseCase(repository = repository),
            getAllCharacterUseCase = GetAllCharacterUseCase(repository = repository),
            searchCharacterUseCase = SearchCharacterUseCase(repository = repository)
        )
    }
}