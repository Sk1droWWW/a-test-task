package com.example.amazingAppsTestTask.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.amazingAppsTestTask.database.CharacterDao
import com.example.amazingAppsTestTask.domain.mapFromDBToCharacterList
import com.example.amazingAppsTestTask.domain.mapToCharacter
import com.example.amazingAppsTestTask.domain.mapToDBCharacter
import com.example.amazingAppsTestTask.domain.model.Character
import com.example.amazingAppsTestTask.network.StarWarsApiService
import com.example.amazingAppsTestTask.network.datasource.CharacterRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PAGE_SIZE = 6

class StarWarsRepository(
    private val api: StarWarsApiService,
    private val dao: CharacterDao
) {
    val favorites: Flow<List<Character>>
        get() = dao.getFavorites().map {
            it.mapFromDBToCharacterList()
        }

    fun searchCharacters(query: String) = Pager(
        pagingSourceFactory = { CharacterRemoteDataSource(api, dao, query) },
        config = PagingConfig(
            pageSize = PAGE_SIZE
        )
    ).flow

    suspend fun deleteCharacter(character: Character?) {
        character?.let { dao.delete(it.mapToDBCharacter()) }
    }

    fun retrieveCharacter(character: Character) : Flow<Character> {
        return dao.get(character.id).map { it.mapToCharacter() }
    }

    suspend fun saveCharacter(character: Character) {
        dao.insert(character.mapToDBCharacter())
    }
}

