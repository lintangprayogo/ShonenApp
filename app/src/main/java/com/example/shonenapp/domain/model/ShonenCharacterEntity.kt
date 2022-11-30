package com.example.shonenapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shonenapp.utils.Constant.SHONEN_CHARACTER_TABLE

@Entity(tableName =SHONEN_CHARACTER_TABLE)
data class ShonenCharacterEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val strength: Int,
    val month: String,
    val day: String,
    val related: List<String>,
    val elements: List<String>,
    val skillSet: List<String>
)
