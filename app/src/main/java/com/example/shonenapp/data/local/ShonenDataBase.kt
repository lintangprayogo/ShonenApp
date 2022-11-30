package com.example.shonenapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shonenapp.data.local.dao.CharacterDao
import com.example.shonenapp.domain.model.ShonenCharacterEntity

@Database(entities = [ShonenCharacterEntity::class], version = 1)
abstract class ShonenDataBase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}