package com.example.amazingAppsTestTask.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.amazingAppsTestTask.database.dto.DBCharacter

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(DBCharacter: DBCharacter)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(DBCharacter: DBCharacter)

    @Delete
    suspend fun delete(DBCharacter: DBCharacter)

    @Query("SELECT * FROM character_table WHERE id = :key")
    fun get(key: Int): LiveData<DBCharacter>

    @Query("SELECT * FROM character_table")
    fun getAll(): LiveData<List<DBCharacter>>

    @Query("SELECT EXISTS(SELECT * FROM character_table WHERE id = :id)")
    fun isCharacterExist(id : Int) : Boolean
}