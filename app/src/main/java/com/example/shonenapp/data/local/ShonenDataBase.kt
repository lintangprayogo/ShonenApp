package com.example.shonenapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.shonenapp.data.local.dao.CharacterDao
import com.example.shonenapp.data.local.dao.RemoteKeyDao
import com.example.shonenapp.domain.model.ShonenCharacterEntity
import com.example.shonenapp.domain.model.ShonenCharacterRemoteKeyEntity

@TypeConverters(value = [DatabaseConverter::class])
@Database(entities = [ShonenCharacterEntity::class,ShonenCharacterRemoteKeyEntity::class], version = 1)
abstract class ShonenDataBase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeyDao():RemoteKeyDao
}