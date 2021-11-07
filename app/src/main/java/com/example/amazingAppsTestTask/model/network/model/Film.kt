package com.example.amazingAppsTestTask.model.network.model

import com.squareup.moshi.Json

data class Film(
    @Json(name = "title")           override val title: String = "",
    @Json(name = "director")        val director: String = "",
    @Json(name = "producer")        val producer: String = "",
    @Json(name = "opening_crawl")   val openingCrawl: String = ""
) : SWModel()