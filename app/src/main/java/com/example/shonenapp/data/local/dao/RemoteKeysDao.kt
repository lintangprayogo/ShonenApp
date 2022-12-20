package com.example.shonenapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shonenapp.domain.model.ShonenCharacterRemoteKeysEntity
import com.example.shonenapp.domain.model.ShonenCharacterRemoteKeysEntry

@Dao
interface RemoteKeysDao {
    @Query("SELECT * FROM ShonenCharacterRemoteKeys where id =:characterId")
    suspend fun getRemoteKey(characterId:Long):ShonenCharacterRemoteKeysEntry?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(remoteKeyEntity: List<ShonenCharacterRemoteKeysEntity>)

    @Query("DELETE FROM ShonenCharacterRemoteKeys")
    suspend fun deleteAll()
}