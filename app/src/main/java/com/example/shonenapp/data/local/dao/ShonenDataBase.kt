package com.example.shonenapp.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shonenapp.domain.model.ShonenCharacterEntity
import com.example.shonenapp.domain.model.ShonenCharacterRemoteKeysEntity

@TypeConverters(value = [DatabaseConverter::class])
@Database(entities = [ShonenCharacterEntity::class,ShonenCharacterRemoteKeysEntity::class], version = 1)
abstract class ShonenDataBase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeyDao():RemoteKeysDao
}