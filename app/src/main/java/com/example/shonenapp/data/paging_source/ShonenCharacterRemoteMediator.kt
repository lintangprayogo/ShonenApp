package com.example.shonenapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.shonenapp.data.local.dao.ShonenDataBase
import com.example.shonenapp.data.remote.ShonenApiService
import com.example.shonenapp.domain.model.ShonenCharacterEntity
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import com.example.shonenapp.domain.model.ShonenCharacterRemoteKeysEntity
import com.example.shonenapp.domain.model.ShonenCharacterRemoteKeysEntry
import java.util.*
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ShonenCharacterRemoteMediator @Inject constructor(
    private val shonenApiService: ShonenApiService,
    private val shonenDataBase: ShonenDataBase
) : RemoteMediator<Int, ShonenCharacterEntry>() {

    private val characterDao = shonenDataBase.characterDao()
    private val remoteKeyDao = shonenDataBase.remoteKeyDao()

    override suspend fun initialize(): InitializeAction {
        val curentTime = System.currentTimeMillis()
        val lastUpdated = remoteKeyDao.getRemoteKey(1)?.lastUpdated ?: 0L
        val cacheTimeOut = 5 * 60 * 24

        val difMinutes = (curentTime - lastUpdated) / (60 * 1000)

        return if (difMinutes > cacheTimeOut) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ShonenCharacterEntry>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getClosestRemoteKeyToCurentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstime(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val respose = shonenApiService.getAllShonenCharacters(page = page)

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
                            id = data.id,
                            lastUpdated = respose.lastUpdated
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
                            strength = data.strength,
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

    private suspend fun getClosestRemoteKeyToCurentPosition(state: PagingState<Int, ShonenCharacterEntry>): ShonenCharacterRemoteKeysEntry? {
        return state.anchorPosition?.let { pos ->
            state.closestItemToPosition(pos)?.id?.let { id ->
                remoteKeyDao.getRemoteKey(id)
            }
        }
    }

    private suspend fun getRemoteKeysForFirstime(state: PagingState<Int, ShonenCharacterEntry>): ShonenCharacterRemoteKeysEntry? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                remoteKeyDao.getRemoteKey(character.id)
            }
    }

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int, ShonenCharacterEntry>): ShonenCharacterRemoteKeysEntry? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                remoteKeyDao.getRemoteKey(character.id)
            }
    }

   /* private fun parseMilis(milis: Long): String {
        val date = Date(milis)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
        return format.format(date)
    }*/
}