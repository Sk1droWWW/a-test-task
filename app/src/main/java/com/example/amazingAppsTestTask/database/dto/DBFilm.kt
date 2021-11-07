package com.example.amazingAppsTestTask.database.dto

import androidx.room.Entity

@Entity
data class DBFilm(
    val id: Int,
    val title: String,
    val director: String,
    val date: String
)
