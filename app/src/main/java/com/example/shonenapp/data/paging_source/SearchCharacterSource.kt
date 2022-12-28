package com.example.shonenapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shonenapp.data.remote.ShonenApiService
import com.example.shonenapp.domain.model.ShonenCharacterEntry
import javax.inject.Inject

class SearchCharacterSource @Inject constructor(
    private val apiService: ShonenApiService,
    val query: String
) : PagingSource<Int, ShonenCharacterEntry>() {

    override fun getRefreshKey(state: PagingState<Int, ShonenCharacterEntry>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShonenCharacterEntry> {
        return try {
            val response = apiService.searchShonenCharacters(page = 1, name = query)
            val data = response.characters.map {
                ShonenCharacterEntry(
                    id = it.id,
                    name = it.name,
                    elements = it.elements,
                    strength = it.strength,
                    related = it.related,
                    month = it.month,
                    rating = it.rating,
                    image = it.image,
                    day = it.day,
                    about = it.about,
                    skillSet = it.skillSet,
                )
            }

            if (data.isNotEmpty()) {
                LoadResult.Page(
                    data = data,
                    prevKey = response.prevPage,
                    nextKey = response.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}