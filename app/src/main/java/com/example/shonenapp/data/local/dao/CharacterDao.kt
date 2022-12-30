package com.example.shonenapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shonenapp.domain.model.ShonenCharacterEntity
import com.example.shonenapp.domain.model.ShonenCharacterEntry

@Dao
interface CharacterDao {

    @Query("SELECT * FROM ShonenCharacter order by id ASC")
    fun getAllCharacter(): PagingSource<Int, ShonenCharacterEntry>

    @Query("SELECT * FROM ShonenCharacter where id=:id")
    suspend fun getSelectedCharacter(id: Long): ShonenCharacterEntry

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacter(list: List<ShonenCharacterEntity>)


    @Query("DELETE FROM ShonenCharacter")
    suspend fun deleteAll()
}