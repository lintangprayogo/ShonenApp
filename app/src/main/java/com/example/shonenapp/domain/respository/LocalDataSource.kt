package com.example.shonenapp.domain.respository

import com.example.shonenapp.domain.model.ShonenCharacterEntry

interface LocalDataSource {
    suspend fun getSelectedCharacter(id:Long):ShonenCharacterEntry
}