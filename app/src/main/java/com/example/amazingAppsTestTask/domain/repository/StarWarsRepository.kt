package com.example.amazingAppsTestTask.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.amazingAppsTestTask.database.CharacterDao
import com.example.amazingAppsTestTask.database.dto.CharacterFilmCrossRef
import com.example.amazingAppsTestTask.domain.mapFromDBToCharacterList
import com.example.amazingAppsTestTask.domain.mapToDBCharacter
import com.example.amazingAppsTestTask.domain.mapToDbFilm
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
        character?.let { character ->
            character.films.forEach {
                if (it != null) {
                    dao.delete(CharacterFilmCrossRef(character.id, it.id))
                }
            }

            dao.delete(character.mapToDBCharacter())
        }
    }

/*    fun retrieveCharacter(character: Character) : Flow<Character> {
        return dao.get(character.id).map { it.mapToCharacter() }
    }*/

    suspend fun saveCharacter(character: Character) {
        character.films.forEach {
            if (it != null) {
                dao.insert(it.mapToDbFilm())
                dao.insert(CharacterFilmCrossRef(character.id, it.id))
            }
        }

        dao.insert(character.mapToDBCharacter())
    }
}

