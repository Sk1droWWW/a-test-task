package com.example.amazingAppsTestTask.domain

import com.example.amazingAppsTestTask.domain.model.Character
import com.example.amazingAppsTestTask.domain.model.Film
import com.example.amazingAppsTestTask.network.dto.NetworkCharacter
import com.example.amazingAppsTestTask.network.dto.NetworkFilm


fun NetworkCharacter.mapToCharacter() : Character {
    return Character(
        id = this.id,
        name = this.name,
        height = this.height,
        mass = this.mass,
        hairColor = this.hairColor,
        skinColor = this.skinColor,
        eyeColor = this.eyeColor,
        birthYear = this.birthYear,
        gender = this.gender,
        films = this.relatedFilms?.mapToFilmList() ?: listOf()
    )
}

fun List<NetworkCharacter>.mapToCharacterList() = this.map { it.mapToCharacter() }

fun NetworkFilm.mapToFilm() = Film(
        id = this.id,
        title = this.title,
        openingCrawl = this.openingCrawl,
        director = this.director,
        producer = this.producer,
    )

fun List<NetworkFilm>.mapToFilmList() = this.map { it.mapToFilm() }
