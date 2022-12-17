package com.example.shonenapp.data.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.models.ShonenCharacterResponse
import com.example.shonenapp.data.local.dao.ShonenDataBase
import com.example.shonenapp.data.remote.ShonenApiService
import com.example.shonenapp.domain.model.ShonenCharacterEntity
import com.example.shonenapp.domain.model.ShonenCharacterRemoteKeysEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ShonenCharacterRemoteMediator @Inject constructor(
    val shonenApiService: ShonenApiService,
    val shonenDataBase: ShonenDataBase
) : RemoteMediator<Int, ShonenCharacterResponse>() {

    private val characterDao = shonenDataBase.characterDao()
    private val remoteKeyDao = shonenDataBase.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ShonenCharacterResponse>
    ): MediatorResult {
        return try {
            val respose = shonenApiService.getAllShonenCharacters(page = 1)

            shonenDataBase.withTransaction {
                if (respose.characters.isNotEmpty()) {
                    if (loadType == LoadType.REFRESH) {
                        characterDao.deleteAll()
                        remoteKeyDao.deleteAll()
                    }
                    val prevPage = respose.prevPage
                    val nextPage = respose.nextPage

                    val keys = respose.characters.map { data ->
                        ShonenCharacterRemoteKeysEntity(
                            prevPage = prevPage,
                            nextPage = nextPage,
                            id = data.id
                        )
                    }

                    val characters = respose.characters.map { data ->
                        ShonenCharacterEntity(
                            id = data.id,
                            about = data.about,
                            day = data.day,
                            elements = data.elements,
                            image = data.image,
                            month = data.month,
                            name = data.name,
                            rating = data.rating,
                            related = data.related,
                            skillSet = data.skillSet,
                            strength = data.strength
                        )
                    }

                    remoteKeyDao.insertRemoteKey(keys)
                    characterDao.insertAllCharacter(characters)
                }
                MediatorResult.Success(endOfPaginationReached = respose.nextPage == null)
            }
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

}