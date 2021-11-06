package com.example.amazingAppsTestTask.model.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character (
    @Json(name = "height")      val height: String,
    @Json(name = "mass")        val mass: String,
    @Json(name = "gender")      val gender: String,
    @Json(name = "birth_year")  val birthYear: String,
    @Json(name = "hair_color")  val hairColor: String,
    @Json(name = "skin_color")  val skinColor: String,
    @Json(name = "eye_color")   val eyeColor: String,
    @Json(name = "films")       override val relatedFilms: List<String>?
) : SWModel()
