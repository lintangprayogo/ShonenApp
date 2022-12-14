package com.example.shonenapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.shonenapp.data.local.dao.ShonenDataBase
import com.example.shonenapp.data.paging_source.SearchCharacterSource
import com.example.shonenapp.data.paging_source.ShonenCharacterRemoteMediator
import com.example.shonenapp.data.remote.ShonenApiService
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import com.example.shonenapp.domain.respository.RemoteDataSource
import com.example.shonenapp.utils.Constant.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val shonenApiService: ShonenApiService,
    private val shonenDataBase: ShonenDataBase
) : RemoteDataSource {

    private val characterDao = shonenDataBase.characterDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllCharacter(): Flow<PagingData<ShonenCharacterEntry>> {
        val pagingSourceFactory = { characterDao.getAllCharacter() }
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            remoteMediator = ShonenCharacterRemoteMediator(
                shonenApiService = shonenApiService,
                shonenDataBase = shonenDataBase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow

    }

    override fun searchCharacter(query: String): Flow<PagingData<ShonenCharacterEntry>> {
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            pagingSourceFactory = {
                SearchCharacterSource(shonenApiService, query)
            }
        ).flow
    }

}