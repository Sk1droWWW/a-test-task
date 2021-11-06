package com.example.amazingAppsTestTask.model.network.model

import com.squareup.moshi.Json

data class SWModelList<T> (
    @Json(name = "next")
    val next: String? = null,

    @Json(name = "previous")
    val previous: String? = null,

    @Json(name = "count")
    val count: Int = 0,

    @Json(name ="results")
    val results: List<T>? = null
)