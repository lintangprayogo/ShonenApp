package com.example.shonenapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shonenapp.data.local.ShonenDataBase
import com.example.shonenapp.utils.Constant.SHONEN_CHARACTER_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
object DbModule {

    @Provides
    fun provideDb(@ApplicationContext context: Context): RoomDatabase =
        Room.databaseBuilder(context, ShonenDataBase::class.java, SHONEN_CHARACTER_DB)
            .build()

}