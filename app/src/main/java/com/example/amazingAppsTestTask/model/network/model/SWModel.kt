package com.example.amazingAppsTestTask.model.network.model

import com.squareup.moshi.Json

open class SWModel {

    @Json(name = "name")
    var name: String = ""
    @Json(name = "url")
    var url: String = ""

    open val title: String
        get() = name

    /**
     * Extract id value from url
     *
     * @return id String
     */
    // example: https://swapi.co/api/films/2/
    @Json(name = "id")
    val id: String
        get() {
            val string = url.split("/".toRegex()).dropLastWhile {
                it.isEmpty()
            }.toTypedArray()
            return string[string.size - 1]
        }

    open val relatedFilms: List<String>?
        get() = null
}