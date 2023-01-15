package com.example.shonenapp.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shonenapp.domain.model.ShonenCharacterEntity
import com.example.shonenapp.domain.model.ShonenCharacterRemoteKeysEntity

@TypeConverters(value = [DatabaseConverter::class])
@Database(entities = [ShonenCharacterEntity::class,ShonenCharacterRemoteKeysEntity::class], version = 1)
abstract class ShonenDataBase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeyDao():RemoteKeysDao

    companion object{
        fun create(context:Context,useInMemory:Boolean ):ShonenDataBase{
            val dataBaseBuilder = if(useInMemory){
                Room.inMemoryDatabaseBuilder(context,ShonenDataBase::class.java)
            }else{
                Room.databaseBuilder(context,ShonenDataBase::class.java,"test_database.db")
            }
            return dataBaseBuilder.fallbackToDestructiveMigration().build()
        }
    }
}