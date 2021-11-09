package com.example.amazingAppsTestTask.domain.model

import java.io.Serializable

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
    var favorite: Boolean = false,
    val films: List<String> = emptyList(),
) : Serializable

