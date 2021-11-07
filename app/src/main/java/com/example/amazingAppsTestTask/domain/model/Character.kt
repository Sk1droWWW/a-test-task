package com.example.amazingAppsTestTask.domain.model

data class Character(
    val id: String = "",
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String = "",
    val skinColor: String = "",
    val eyeColor: String = "",
    val birthYear: String = "",
    val gender: String,
    val films: List<Film>,
)

