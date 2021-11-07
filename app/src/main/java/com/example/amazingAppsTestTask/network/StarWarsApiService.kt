package com.example.amazingAppsTestTask.network

import com.example.amazingAppsTestTask.network.dto.NetworkCharacter
import com.example.amazingAppsTestTask.network.dto.NetworkFilm
import com.example.amazingAppsTestTask.network.dto.NetworkModelList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApiService {

    @Headers("Content-Type: application/json")
    @GET("people")
    suspend fun searchCharacter(
        @Query("page") page: Int,
        @Query("search") name: String?): Response<NetworkModelList<NetworkCharacter>>

    @GET("people" + "/{id}")
    fun getCharacter(@Path("id") id: Int): Response<NetworkCharacter>

    @GET("films" + "/{id}")
    fun getFilm(@Path("id") id: Int): Response<NetworkFilm>

    companion object {
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun getApiService(): StarWarsApiService = Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(StarWarsApiService::class.java)
    }
}