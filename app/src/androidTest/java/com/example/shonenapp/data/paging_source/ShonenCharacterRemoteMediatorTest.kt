@file:OptIn(ExperimentalPagingApi::class)

package com.example.shonenapp.data.paging_source

import androidx.paging.*
import androidx.paging.RemoteMediator.MediatorResult
import androidx.test.core.app.ApplicationProvider
import com.example.shonenapp.data.local.dao.ShonenDataBase
import com.example.shonenapp.data.remote.FakeShonenApi2
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class ShonenCharacterRemoteMediatorTest {
    private lateinit var shonenApiService: FakeShonenApi2
    private lateinit var shonenDataBase: ShonenDataBase

    @Before
    fun setup() {
        shonenApiService = FakeShonenApi2()
        shonenDataBase = ShonenDataBase.create(ApplicationProvider.getApplicationContext(), false)
    }

    @After
    fun cleanUp() {
        shonenDataBase.clearAllTables()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() {
        runBlocking {
            val remoteMediator = ShonenCharacterRemoteMediator(
                shonenApiService = shonenApiService,
                shonenDataBase = shonenDataBase
            )

            val pagingState = PagingState<Int, ShonenCharacterEntry>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)

            assertTrue(result is MediatorResult.Success)
            assertFalse((result as MediatorResult.Success).endOfPaginationReached)
        }
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData() {
        runBlocking {
            shonenApiService.clearData()
            val remoteMediator = ShonenCharacterRemoteMediator(
                shonenApiService = shonenApiService,
                shonenDataBase = shonenDataBase
            )

            val pagingState = PagingState<Int, ShonenCharacterEntry>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)

            assertTrue(result is MediatorResult.Success)
            assertTrue((result as MediatorResult.Success).endOfPaginationReached)
        }
    }

    @Test
    fun refreshLoadWhenError() {
        runBlocking {
            shonenApiService.addException()
            val remoteMediator = ShonenCharacterRemoteMediator(
                shonenApiService = shonenApiService,
                shonenDataBase = shonenDataBase
            )

            val pagingState = PagingState<Int, ShonenCharacterEntry>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)

            assertTrue(result is MediatorResult.Error)
        }
    }


}