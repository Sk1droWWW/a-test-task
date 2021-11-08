package com.example.amazingAppsTestTask.domain

import com.example.amazingAppsTestTask.database.CharacterDao
import com.example.amazingAppsTestTask.database.dto.DBCharacter
import com.example.amazingAppsTestTask.domain.model.Character
import com.example.amazingAppsTestTask.domain.model.Film
import com.example.amazingAppsTestTask.network.dto.NetworkCharacter
import com.example.amazingAppsTestTask.network.dto.NetworkFilm


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
        gender = this.gender
    )

fun DBCharacter.mapToCharacter() =
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
        favorite = true
    )

fun List<DBCharacter>.mapFromDBToCharacterList() =
    this.map { it.mapToCharacter() }

fun NetworkCharacter.mapToCharacter() =
    Character(
        id = this.id,
        name = this.name,
        height = this.height,
        mass = this.mass,
        hairColor = this.hairColor,
        skinColor = this.skinColor,
        eyeColor = this.eyeColor,
        birthYear = this.birthYear,
        gender = this.gender
//        films = this.relatedFilms?.mapToFilmList() ?: listOf()
    )

fun List<NetworkCharacter>.mapFromNetworkToCharacterList(dao: CharacterDao) =
    this.map {
        val result = it.mapToCharacter()
        result.favorite = dao.isCharacterExist(it.id)
        result
    }

fun NetworkFilm.mapToFilm() = Film(
        id = this.id,
        title = this.title,
        openingCrawl = this.openingCrawl,
        director = this.director,
        producer = this.producer,
    )

fun List<NetworkFilm>.mapToFilmList() = this.map { it.mapToFilm() }
