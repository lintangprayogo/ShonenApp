package com.example.shonenapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shonenapp.domain.model.ShonenCharacterRemoteKeyEntity
import com.example.shonenapp.domain.model.ShonenCharacterRemoteKeyEntry

@Dao
interface RemoteKeyDao {
    @Query("SELECT * FROM ShonenCharacterRemoteKey where id =:id")
    suspend fun getRemoteKey(id:Long):ShonenCharacterRemoteKeyEntry?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(remoteKeyEntity: List<ShonenCharacterRemoteKeyEntity>)

    @Query("DELETE FROM ShonenCharacterRemoteKey")
    suspend fun deleteRemoteKey()
}