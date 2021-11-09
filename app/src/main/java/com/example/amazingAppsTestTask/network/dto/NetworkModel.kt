package com.example.amazingAppsTestTask.network.dto

import com.squareup.moshi.Json

open class NetworkModel {

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
    val id: String
        get() {
            val string = url.split("/".toRegex()).dropLastWhile {
                it.isEmpty()
            }.toTypedArray()
            return string[string.size - 1]
        }
}