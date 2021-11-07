package com.example.amazingAppsTestTask.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.amazingAppsTestTask.database.CharacterDatabase
import com.example.amazingAppsTestTask.database.dto.DBCharacter
import com.example.amazingAppsTestTask.network.StarWarsApiService
import com.example.amazingAppsTestTask.network.datasource.CharacterRemoteDataSource

class StarWarsRepository(
    private val api: StarWarsApiService,
    private val database: CharacterDatabase
) {
    fun searchCharacters(query: String) = Pager(
        pagingSourceFactory = { CharacterRemoteDataSource(api, query) },
        config = PagingConfig(
            pageSize = 6
        )
    ).flow

    fun deleteCharacter(DBCharacter: DBCharacter) {

    }

    fun retrieveCharacter() {

    }

    fun saveCharacter(DBCharacter: DBCharacter) {

    }
}

