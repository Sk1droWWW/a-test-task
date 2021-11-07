package com.example.amazingAppsTestTask.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class DBCharacter(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = "",

    var name: String,
    var height: String,
    var mass: String,
    var hairColor: String = "",
    var skinColor: String = "",
    var eyeColor: String = "",
    var birthYear: String = "",
    var gender: String,
//    var films: Int,
)
