package com.example.amazingAppsTestTask.network.dto

import com.squareup.moshi.Json

data class NetworkModelList<T> (
    @Json(name = "next")
    val next: String? = null,

    @Json(name = "previous")
    val previous: String? = null,

    @Json(name = "count")
    val count: Int = 0,

    @Json(name = "results")
    val results: List<T>? = null
)