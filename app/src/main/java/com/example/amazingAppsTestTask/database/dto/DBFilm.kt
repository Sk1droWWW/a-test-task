package com.example.amazingAppsTestTask.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBFilm(
    @PrimaryKey
    @ColumnInfo(name = "film_id")
    val id: String,
//    @ColumnInfo(name = "parent_character_id")
//    val characterId: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "opening_crawl")
    val openingCrawl: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "director")
    val director: String,
    @ColumnInfo(name = "producer")
    val producer: String,
)
