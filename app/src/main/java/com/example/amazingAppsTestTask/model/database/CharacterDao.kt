package com.example.amazingAppsTestTask.model.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(character: Character)

    @Delete
    suspend fun delete(character: Character)

    @Query("SELECT * FROM character_table WHERE id = :key")
    fun get(key: Int): LiveData<Character>

    @Query("SELECT * FROM character_table")
    fun getAll(): LiveData<List<Character>>

    @Query("SELECT EXISTS(SELECT * FROM character_table WHERE id = :id)")
    fun isCharacterExist(id : Int) : Boolean
}