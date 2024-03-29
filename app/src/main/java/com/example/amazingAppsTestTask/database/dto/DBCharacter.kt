package com.example.amazingAppsTestTask.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class DBCharacter(
    @PrimaryKey
    @ColumnInfo(name = "character_id")
    var id: String = "",
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "height")
    var height: String,
    @ColumnInfo(name = "mass")
    var mass: String,
    @ColumnInfo(name = "hairColor")
    var hairColor: String = "",
    @ColumnInfo(name = "skinColor")
    var skinColor: String = "",
    @ColumnInfo(name = "eyeColor")
    var eyeColor: String = "",
    @ColumnInfo(name = "birthYear")
    var birthYear: String = "",
    @ColumnInfo(name = "gender")
    var gender: String,
)
