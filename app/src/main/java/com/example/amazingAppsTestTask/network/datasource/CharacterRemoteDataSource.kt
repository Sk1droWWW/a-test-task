package com.example.amazingAppsTestTask.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.amazingAppsTestTask.domain.mapFromNetworkToCharacterList
import com.example.amazingAppsTestTask.domain.model.Character
import com.example.amazingAppsTestTask.network.StarWarsApiService

private const val INITIAL_PAGE = 1

class CharacterRemoteDataSource(
    private val apiService: StarWarsApiService,
    private val query: String
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        try {
            val currentLoadingPageKey = params.key ?: INITIAL_PAGE
            val response = apiService.searchCharacter(currentLoadingPageKey, query)
            val responseData = mutableListOf<Character>()
            val data = response.body()?.results?.mapFromNetworkToCharacterList() ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}