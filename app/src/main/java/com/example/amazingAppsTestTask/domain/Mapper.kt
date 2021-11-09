package com.example.amazingAppsTestTask.domain

import com.example.amazingAppsTestTask.database.CharacterDao
import com.example.amazingAppsTestTask.database.dto.CharacterWithFilms
import com.example.amazingAppsTestTask.database.dto.DBCharacter
import com.example.amazingAppsTestTask.database.dto.DBFilm
import com.example.amazingAppsTestTask.domain.model.Character
import com.example.amazingAppsTestTask.domain.model.Film
import com.example.amazingAppsTestTask.network.StarWarsApiService
import com.example.amazingAppsTestTask.network.dto.NetworkCharacter
import com.example.amazingAppsTestTask.network.dto.NetworkFilm


fun CharacterWithFilms.mapToCharacter() =
    Character(
        id = this.character.id,
        name = this.character.name,
        height = this.character.height,
        mass = this.character.mass,
        hairColor = this.character.hairColor,
        skinColor = this.character.skinColor,
        eyeColor = this.character.eyeColor,
        birthYear = this.character.birthYear,
        gender = this.character.gender,
        favorite = true,
        films = this.films.mapToFilmList()
    )

fun NetworkCharacter.mapToCharacter(films: List<Film?>) =
    Character(
        id = this.id,
        name = this.name,
        height = this.height,
        mass = this.mass,
        hairColor = this.hairColor,
        skinColor = this.skinColor,
        eyeColor = this.eyeColor,
        birthYear = this.birthYear,
        gender = this.gender,
        films = films
    )

fun Character.mapToDBCharacter() =
    DBCharacter(
        id = this.id,
        name = this.name,
        height = this.height,
        mass = this.mass,
        hairColor = this.hairColor,
        skinColor = this.skinColor,
        eyeColor = this.eyeColor,
        birthYear = this.birthYear,
        gender = this.gender,
    )

fun DBFilm.mapToFilm() =
    Film (
        id = this.id,
        title = this.title,
        openingCrawl = this.openingCrawl,
        director = this.director,
        producer = this.date,
        date = this.date
        )

fun NetworkFilm.mapToFilm() =
    Film(
        id = this.id,
        title = this.title,
        openingCrawl = this.openingCrawl,
        director = this.director,
        producer = this.producer,
        date = this.date
    )

fun Film.mapToDbFilm() =
    DBFilm(
        id = this.id,
        title = this.title,
        openingCrawl = this.openingCrawl,
        director = this.director,
        producer = this.date,
        date = this.date
    )

fun List<CharacterWithFilms>.mapFromDBToCharacterList() =
    this.map { it.mapToCharacter() }

suspend fun List<NetworkCharacter>.mapFromNetworkToCharacterList(
    api: StarWarsApiService,
    dao: CharacterDao) =
    this.map { character ->
        val networkFilms = character.relatedFilms?.map {
            api.getFilm(getId(it)).body()?.mapToFilm()
        } ?: emptyList()

        val result = character.mapToCharacter(networkFilms)

        result.favorite = dao.isCharacterExist(character.id)
        result
    }

fun List<DBFilm?>.mapToFilmList() = this.map { it?.mapToFilm() }

fun List<Film?>.mapToDBFilm() = this.map { it?.mapToDbFilm() }

private fun getId(url: String): String {
    val string = url.split("/".toRegex()).dropLastWhile {
        it.isEmpty()
    }.toTypedArray()
    return string[string.size - 1]
}
