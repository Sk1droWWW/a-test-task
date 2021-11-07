package com.example.amazingAppsTestTask.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.amazingAppsTestTask.model.datasource.CharacterRemoteDataSource
import com.example.amazingAppsTestTask.model.network.StarWarsApiService

class StarWarsRepository(
    private val api: StarWarsApiService
) {
    fun searchCharacters(query: String) = Pager(
        pagingSourceFactory = { CharacterRemoteDataSource(api, query) },
        config = PagingConfig(
            pageSize = 6
        )
    ).flow
}

