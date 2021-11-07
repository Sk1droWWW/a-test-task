package com.example.amazingAppsTestTask.model.network

import com.example.amazingAppsTestTask.model.network.model.Character
import com.example.amazingAppsTestTask.model.network.model.Film
import com.example.amazingAppsTestTask.model.network.model.SWModelList
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
        @Query("search") name: String?): Response<SWModelList<Character>>

    @GET("people" + "/{id}")
    fun getCharacter(@Path("id") id: Int): Response<Character>

    @GET("films" + "/{id}")
    fun getFilm(@Path("id") id: Int): Response<Film>

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