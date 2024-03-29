package com.example.amazingAppsTestTask.database

import androidx.room.*
import com.example.amazingAppsTestTask.database.dto.CharacterFilmCrossRef
import com.example.amazingAppsTestTask.database.dto.CharacterWithFilms
import com.example.amazingAppsTestTask.database.dto.DBCharacter
import com.example.amazingAppsTestTask.database.dto.DBFilm
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: DBCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: DBFilm?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ref: CharacterFilmCrossRef)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(character: DBCharacter)

    @Delete
    suspend fun delete(character: DBCharacter)

    @Delete
    suspend fun delete(ref: CharacterFilmCrossRef)

    @Query("SELECT * FROM character_film_ref WHERE character_id = :id")
    fun getCharacterFilmRef(id: String): Flow<List<CharacterFilmCrossRef>>

    @Query("SELECT * FROM character_table WHERE character_id = :id")
    fun getCharacter(id: String): Flow<DBCharacter>

    @Query("SELECT * FROM film_table WHERE film_id = :id")
    fun getFilm(id: String): Flow<DBFilm>

    @Transaction
    @Query("SELECT * FROM character_table")
    fun getFavorites(): Flow<List<CharacterWithFilms>>

    @Query("SELECT EXISTS(SELECT * FROM character_table WHERE character_id = :id)")
    fun isCharacterExist(id: String): Boolean
}