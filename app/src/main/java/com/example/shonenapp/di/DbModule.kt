package com.example.shonenapp.di

import android.content.Context
import androidx.room.Room
import com.example.shonenapp.data.local.dao.ShonenDataBase
import com.example.shonenapp.data.repository.LocalDataSourceImpl
import com.example.shonenapp.domain.respository.LocalDataSource
import com.example.shonenapp.utils.Constant.SHONEN_CHARACTER_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DbModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): ShonenDataBase =
        Room.databaseBuilder(context, ShonenDataBase::class.java, SHONEN_CHARACTER_DB)
            .build()


    @Provides
    @Singleton
    fun provideLocalDataSource(shonenDataBase: ShonenDataBase):LocalDataSource = LocalDataSourceImpl(shonenDataBase)

}