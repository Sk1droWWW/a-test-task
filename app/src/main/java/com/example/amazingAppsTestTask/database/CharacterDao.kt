package com.example.amazingAppsTestTask.database

import androidx.room.*
import com.example.amazingAppsTestTask.database.dto.DBCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: DBCharacter)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(character: DBCharacter)

    @Delete
    suspend fun delete(character: DBCharacter)

    @Query("SELECT * FROM character_table WHERE id = :id")
    fun get(id: String): Flow<DBCharacter>

    @Query("SELECT * FROM character_table")
    fun getFavorites(): Flow<List<DBCharacter>>

    @Query("SELECT EXISTS(SELECT * FROM character_table WHERE id = :id)")
    fun isCharacterExist(id : String) : Boolean
}