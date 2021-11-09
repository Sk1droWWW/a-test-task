package com.example.amazingAppsTestTask.database.dto

import androidx.room.*

data class CharacterWithFilms(
    @Embedded val character: DBCharacter,
    @Relation(
        parentColumn = "character_id",
        entityColumn = "film_id",
        associateBy = Junction(CharacterFilmCrossRef::class)
    )
    val films: List<DBFilm>
)

@Entity(primaryKeys = ["character_id", "film_id"])
data class CharacterFilmCrossRef(
    @ColumnInfo(name = "character_id")
    val characterId: String,
    @ColumnInfo(name = "film_id")
    val filmId: String
)
