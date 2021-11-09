package com.example.amazingAppsTestTask.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.amazingAppsTestTask.database.CharacterDao
import com.example.amazingAppsTestTask.database.dto.CharacterFilmCrossRef
import com.example.amazingAppsTestTask.domain.mapFromDBToCharacterList
import com.example.amazingAppsTestTask.domain.mapToDBCharacter
import com.example.amazingAppsTestTask.domain.mapToDbFilm
import com.example.amazingAppsTestTask.domain.mapToFilm
import com.example.amazingAppsTestTask.domain.model.Character
import com.example.amazingAppsTestTask.domain.model.Film
import com.example.amazingAppsTestTask.network.StarWarsApiService
import com.example.amazingAppsTestTask.network.datasource.CharacterRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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

    suspend fun retrieveFilms(character: Character): MutableList<Film> {
        val filmList: MutableList<Film> = mutableListOf()
        if (character.favorite) {
            val refList = dao.getCharacterFilmRef(character.id).first()
            refList.forEach { ref ->
                filmList.add(dao.getFilm(ref.filmId).first().mapToFilm())
            }
        } else {
            character.films.forEach {
                val film = api.getFilm(getId(it)).body()
                if (film != null) {
                    filmList.add(film.mapToFilm())
                }
            }
        }

        return filmList
    }

    suspend fun deleteCharacter(character: Character?) {
        character?.let { character ->
            character.films.forEach {
                dao.delete(CharacterFilmCrossRef(character.id, it))
            }

            dao.delete(character.mapToDBCharacter())
        }
    }

    suspend fun saveCharacterAndFilms(character: Character, films: List<Film>) {
        dao.insert(character.mapToDBCharacter())

        films.forEach {
            dao.insert(it.mapToDbFilm())
            dao.insert(CharacterFilmCrossRef(character.id, it.id))
        }
    }

    suspend fun saveCharacterFromSearchFragment(character: Character) {
        dao.insert(character.mapToDBCharacter())
        character.films.forEach {
            api.getFilm(getId(it)).body()?.mapToFilm()?.mapToDbFilm()
            dao.insert(CharacterFilmCrossRef(character.id, getId(it)))
        }
    }

    private fun getId(url: String): String {
        val string = url.split("/".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()
        return string[string.size - 1]
    }
}

