package com.example.amazingAppsTestTask.model.database

import androidx.room.Entity

@Entity
data class Film(
    val id: Int,
    val title: String,
    val director: String,
    val date: String
)
