package com.example.shonenapp.data.repository

import com.example.shonenapp.data.local.dao.ShonenDataBase
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import com.example.shonenapp.domain.respository.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(shonenDataBase: ShonenDataBase) :
    LocalDataSource {

    val dao = shonenDataBase.characterDao()

    override suspend fun getSelectedCharacter(id: Long): ShonenCharacterEntry {
        return dao.getSelectedCharacter(id)
    }
}