package com.example.amazingAppsTestTask.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkFilm(
    @Json(name = "title")           override val title: String = "",
    @Json(name = "director")        val director: String = "",
    @Json(name = "producer")        val producer: String = "",
    @Json(name = "opening_crawl")   val openingCrawl: String = ""
) : NetworkModel()