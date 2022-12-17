package com.example.shonenapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shonenapp.utils.Constant.SHONEN_CHARACTER_REMOTE_KEY_TABLE

@Entity(tableName =  SHONEN_CHARACTER_REMOTE_KEY_TABLE)
data class ShonenCharacterRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Long,
    val prevPage:Int?,
    val nextPage:Int?,
)
